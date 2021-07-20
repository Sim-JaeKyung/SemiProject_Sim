package web.service;

import java.util.ArrayList;

import web.DAO.ReplyDAO;
import web.util.ProjectException;
import web.vo.ReplyVO;

public class ReplyService {
	ReplyDAO dao;
	
	public ReplyService() {
		dao=new ReplyDAO();
	}

	public void addReply(ReplyVO vo) throws ProjectException {
		dao.addReply(vo);
		
	}

	public ArrayList<ReplyVO> listReply() throws ProjectException {
		return dao.listReply();
	}
    
	
}
