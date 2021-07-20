package web.controller;

import java.io.IOException; 
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import web.service.MemberService;
import web.service.ReplyService;
import web.util.ProjectException;
import web.vo.MemberVO;
import web.vo.ReplyVO;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	MemberService memberService;
	ReplyService replyService;
	
	@Override
	public void init() throws ServletException {
		memberService=new MemberService();
		replyService=new ReplyService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		myService(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		myService(request, response);
	}

	protected void myService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//기본설정

		JSONObject json=new JSONObject();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		String sign=request.getParameter("sign");
		
		//if sign절 시작
		if("memberSignIn".equals(sign)) {
			String name=request.getParameter("name");
			String age=request.getParameter("age");
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			String address=request.getParameter("address");
			//파싱 및 데이터 VO에 담기
			int ageParse= Integer.parseInt(age);
			MemberVO vo=new MemberVO(name, ageParse, id, pw, address);
			System.out.println(vo);
			
			try {
				memberService.insertMember(vo);
				out.append(name+"님 가입되었습니다");
			}catch (ProjectException e) {
				e.printStackTrace();
			}
			
		}else if("login".equals(sign)){
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			MemberVO vo=new MemberVO(id,pw);
			System.out.println(vo);
			
			try {
				String account=memberService.loginMember(vo);
				if(account!=null) {
					HttpSession session=request.getSession();
					session.setAttribute("id", id);
					json.put("id", id);
					out.append(json.toJSONString());
				}else {
					json.put("msg", "로그인 실패");
					out.append(json.toJSONString());
				}
			}catch (ProjectException e) {
				e.printStackTrace();
				json.put("msg", "login 실패");
				out.append(json.toJSONString());
			}
		}else if("replyInsert".equals(sign)) { //댓글달기
			String id=request.getParameter("id");
			HttpSession session=request.getSession(false);
			if(session==null) {
				out.append("해킹이 의심됩니다.");
			}else {
				if(session.getAttribute("id").equals(id)) {
					String content = request.getParameter("content");
					ReplyVO vo=new ReplyVO(id,content,0,null);
					System.out.println(vo);
					try {
						replyService.addReply(vo);
						out.append("댓글이 등록되었습니다");
					}catch (ProjectException e) {
					    out.append(e.getMessage());
					}
				}else {
					out.append("해킹이 의심됩니다");
				}
			}
		}else if("replyList.do".equals(sign)) { //댓글노출
			try {
				ArrayList<ReplyVO> replyList=replyService.listReply();
				JSONArray jsonArray=new JSONArray();
				for(ReplyVO vo:replyList) {
					JSONObject jo=new JSONObject();
					jo.put("articleNo", vo.getArticleNo());
					jo.put("id", vo.getId());
					jo.put("content", vo.getContent());
					jo.put("writeDate", vo.getWriteDate().toString());
					jsonArray.add(jo);
				}
				out.append(jsonArray.toJSONString());
			}catch (ProjectException e) {
			}
			
		}
	}
	
}
