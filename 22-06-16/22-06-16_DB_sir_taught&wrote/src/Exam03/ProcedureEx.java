package Exam03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement; //동적
import java.sql.CallableStatement;


public class ProcedureEx {
	public static void main(String[] args){
			
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "ddit";
			String password = "java";
			
			Connection conn = null; //연결객체
			ResultSet rs = null; //결과보관객체
			PreparedStatement pstmt = null;//동적SQL명령객체
			CallableStatement cstmt = null;
			
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			StringBuilder sb = new StringBuilder();
			sb.append(" { CALL PROC_INSERT_LPROD(?,?)} ");
			cstmt=conn.prepareCall(sb.toString()); //sb는 string buffer class 객체이므로 .toString() 메소드 호출
			
			cstmt.setString(1,"P510");
			cstmt.setString(2, "임산가공식품");
			
			int res = cstmt.executeUpdate(); //프로시저가 호출되어 실행 됨
			if(res==0){
				System.out.println("입력실패");
			}else{
				System.out.println("입력성공");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null) rs.close();
				if(cstmt!=null) cstmt.close();
				if(conn!=null) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
}
