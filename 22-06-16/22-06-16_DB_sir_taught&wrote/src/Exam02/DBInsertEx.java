//LPROD 테이블에 사용자가 입력한 분류코드와 분류명으로 자료 삽입

package Exam02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; //정적
import java.sql.PreparedStatement; //동적
import java.util.Scanner;

public class DBInsertEx {

	public static void main(String[] args){
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ddit";
		String password = "java";
		
		Connection conn = null; //연결객체
		ResultSet rs = null;
		PreparedStatement pstmt = null;//동적SQL명령객체
		Scanner sc = new Scanner(System.in);
		
	try{
		Class.forName(driver);
		conn = DriverManager.getConnection(url,user,password);
		StringBuilder sql = new StringBuilder();
		
					//가능한 구분위해 띄워쓸 것
		sql.append(" INSERT INTO LPROD( LPROD_ID, LPROD_GU , LPROD_NM ) ");
		sql.append(" VALUES(SEQ_LPROD_ID.NEXTVAL,?,?) ");
	//											 1,2	
		System.out.println(" 분류코드(exP501) 입력 : ");
		String gid = sc.nextLine();
		
		System.out.println(" 분류명 입력 : ");
		String gname = sc.nextLine();
		
		pstmt = conn.prepareStatement(sql.toString()); //sql로 그대로 쓰면 안되고 string메소드 불러와야함
		pstmt.setString(1,gid);
		pstmt.setString(2,gname);
		
		int res = pstmt.executeUpdate();
		
			if(res==0){
				System.out.println("입력실패");
			}else{
				System.out.println("자료추가 성공");
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt!=null) pstmt.close();
				if(rs!=null) rs.close();
				if(conn!=null) conn.close(); 
			}catch(Exception e){
				e.printStackTrace();
			}			
		}
	}// main
}//class
