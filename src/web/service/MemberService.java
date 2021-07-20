package web.service;

import web.DAO.MemberDAO;
import web.util.ProjectException;
import web.vo.MemberVO;

public class MemberService {
    MemberDAO dao;

	public MemberService() {
		super();
		dao=new MemberDAO();
	}
	
	public void insertMember(MemberVO vo) throws ProjectException{
		dao.insertMember(vo);
	}

	public String loginMember(MemberVO vo) throws ProjectException {
		return dao.selectMember(vo);
	}
    
    
}
