package service;

import java.sql.SQLException;

import Project_DTO.memberDTO;
import dao.MemberDAO;

public class MemberService {
	//싱글톤
	//Instance
    private static MemberService instance = new MemberService();
    //private construct
    private MemberService() {}
    public static MemberService getInstance() {
        return instance;
    }
    
    MemberDAO memberDAO = MemberDAO.getInstance();
    
    //로그인 select
  	public memberDTO selectLogin(memberDTO member) throws SQLException{
  		return memberDAO.selectLogin(member);
  	}
}
