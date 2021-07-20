package web.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import web.util.ProjectException;
import web.vo.MemberVO;

public class MemberDAO {
    DataSource ds;

	
	public MemberDAO() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds= (DataSource) envCtx.lookup("jdbc/MyDB");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public void insertMember(MemberVO vo) throws ProjectException{
    	Connection con=null;
    	PreparedStatement ps=null;
    	
    	try {
    		con=ds.getConnection();
    		
    		ps=con.prepareStatement("INSERT INTO member(id, pw, name, address, age) VALUES(?,?,?,?,?)");
    		
    		ps.setString(1, vo.getId());
    		ps.setString(2, vo.getPw());
    		ps.setString(3, vo.getName());
    		ps.setString(4, vo.getAddress());
    		ps.setInt(5, vo.getAge());
    		
    		int i=ps.executeUpdate();
    		System.out.println(i+"행 삽입 완료");
    	} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("회원가입 실패");
		}finally {
			try {
				ps.close();
				con.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
    }
	public String selectMember(MemberVO vo) throws ProjectException {
    	Connection con=null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	
    	try {
    		con=ds.getConnection();
    		
    		ps=con.prepareStatement("SELECT * FROM member WHERE id=? and pw=?");
    		
    		ps.setString(1, vo.getId());
    		ps.setString(2, vo.getPw());
    		
    		rs=ps.executeQuery();
    		if(rs.next()) {
    			return rs.getString("id");
    		}
    		return null;
    	} catch (Exception e) {
			e.printStackTrace();
			throw new ProjectException("로그인 실패");
		}finally {
			try {
				ps.close();
				con.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
    
}
