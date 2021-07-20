package web.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import web.util.ProjectException;
import web.vo.ReplyVO;

public class ReplyDAO {
    DataSource ds;

	public ReplyDAO() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds= (DataSource) envCtx.lookup("jdbc/MyDB");			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void addReply(ReplyVO vo) throws ProjectException {
	    Connection con=null;
	    PreparedStatement ps=null, ps2=null;
	    ResultSet rs=null;
		
	    try {
	    	con=ds.getConnection();
	    	ps=con.prepareStatement("INSERT INTO reply(articleno, id, content, writedate) VALUES(?,?,?,sysdate)");
	    	ps2=con.prepareStatement("SELECT max(articleNO) FROM reply");
	    	rs=ps2.executeQuery();
	    	int articleNO=0;
	    	if(rs.next()) {
	    		articleNO=rs.getInt(1)+1;
	    		System.out.println(articleNO+"번째 댓글 등록 중");
	    	}
	    	ps.setInt(1, articleNO);
	    	ps.setString(2, vo.getId());
	    	ps.setString(3, vo.getContent());
	    	int i=ps.executeUpdate();
	    	System.out.println(i+"행이 insert되었습니다.");
	    }catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("댓글등록 실패");
		}finally {
			try {
				rs.close();
				ps2.close();
				ps.close();
				con.close();				
			}catch (Exception e) {
			}
		}
	}

	public ArrayList<ReplyVO> listReply() throws ProjectException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			con=ds.getConnection();
			ps=con.prepareStatement("SELECT * FROM reply order by articleno desc");
			rs=ps.executeQuery();
			ArrayList<ReplyVO> list=new ArrayList<ReplyVO>();
			while(rs.next()) {
				int articleno=rs.getInt("articleno");
				String id=rs.getString("id");
				String content=rs.getString("content");
				Date writeDate=rs.getDate("writedate");
				
				ReplyVO vo=new ReplyVO(id, content, articleno, writeDate);
				list.add(vo);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("댓글 보이기 실패");
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			}catch (Exception e) {
			}
		}
	}
    
	
    
}
