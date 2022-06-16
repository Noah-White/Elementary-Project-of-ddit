package Exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet; //SELECT 문 쓸 때 필요함
import java.sql.SQLException;
import java.sql.Statement; //정적

public class DBConnection {

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ddit";
		String password = "java";
		
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null; //SQL명령객체

		
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
			
			String sql = " SELECT MEM_ID , MEM_NAME , MEM_MILEAGE "
						 + " FROM MEMBER "
						 + " WHERE SUBSTR(MEM_REGNO2,1,1) = '2' ";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); //select 조회 / 정적
			
			
			
			while(rs.next()){
				String id = rs.getString("MEM_ID");
				String name = rs.getString("MEM_NAME"); //1써도 되긴함 (ORDER BY 처럼)
				int mileage = rs.getInt("MEM_MILEAGE");
				
				//System.out.println("------------------------");
				System.out.println(id+"\t"+name+"\t"+mileage);
			}
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if(rs!=null) rs.close();
					if(stmt!=null) stmt.close();
					if(conn!=null) conn.close(); 
				}catch(Exception e){
					e.printStackTrace();
				}		
			}
		
		

	}// main

}//class
