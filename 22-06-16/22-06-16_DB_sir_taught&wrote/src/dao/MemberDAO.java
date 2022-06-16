package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Project.DBUtil;
import Project_DTO.memberDTO;

public class MemberDAO {
	Connection conn = null; // 연결객체
	ResultSet rs = null;
	PreparedStatement pstmt = null;// 동적SQL명령객체
	
	//싱글톤
	//Instance
    private static MemberDAO instance = new MemberDAO();
    //private construct
    private MemberDAO() {}
    public static MemberDAO getInstance() {
        return instance;
    }
	
	//로그인 select
	public memberDTO selectLogin(memberDTO member) throws SQLException{
			memberDTO dto = new memberDTO();
			
	      try {
	         conn = DBUtil.dbConnection();
	         StringBuilder sql = new StringBuilder();

	         // 가능한 구분위해 띄워쓸 것
	         sql.append(" SELECT MEM_ID  ");
	         sql.append(" ,  MEM_PW      ");
	         sql.append(" ,  MEM_NM      ");
	         sql.append(" ,  MEM_TEL_NUM ");
	         sql.append(" ,  REGNO1      ");
	         sql.append(" ,  REGNO2      ");
	         sql.append(" ,  MEM_ADDR    ");
	         sql.append(" ,  MILEAGE     ");
	         sql.append(" ,  MEM_GRADE   ");
	         sql.append(" ,  MEM_DELETE  ");
	         sql.append(" FROM   MEMBER	 ");
	   		 sql.append(" WHERE 1 = 1	 ");
			 sql.append(" AND    MEM_ID=? AND MEM_PW=?");

	         pstmt = conn.prepareStatement(sql.toString());
	         //입력받은 아이디/비밀번호
	         pstmt.setString(1, member.getMem_id());
	         pstmt.setString(2, member.getMem_pw());

	         rs = pstmt.executeQuery();

	         if (rs.next()){
	        	String memId = rs.getString(1);
	        	String memPw = rs.getString(2);
	        	String memNm = rs.getString(3);
	        	String memTelNum = rs.getString(4);
	        	int regno1 = rs.getInt(5);
	        	int regno2 = rs.getInt(6);
	        	String memAddr = rs.getString(7);
	        	int mileage = rs.getInt(8);
	        	String memGrade = rs.getString(9);
	        	String memDelete = rs.getString(10);
	        	
	        	dto.setMem_id(memId);
	        	dto.setMem_pw(memPw);
	        	dto.setMem_nm(memNm);
	        	dto.setMem_tel_num(memTelNum);
	        	dto.setRegno1(regno1);
	        	dto.setRengno2(regno2);
	        	dto.setMem_addr(memAddr);
	        	dto.setMileage(mileage);
	        	dto.setMem_grade(memGrade);
	        	dto.setMem_delete(memDelete);
	         }
	         return dto;
	      } catch (SQLException e) {
	         e.printStackTrace();
	         return null;
	      } finally {
	         DBUtil.close(rs);
	         DBUtil.close(pstmt);
	         DBUtil.close(conn);
	      }
	}
}
