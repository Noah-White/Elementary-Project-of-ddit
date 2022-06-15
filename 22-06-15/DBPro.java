//22-06-14_Teacher charged class taught us
package project.elementary.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBPro {

	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement pstmt;
	
	public void close() {
		if(rs != null)
			try{
				rs.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		if(stmt != null)
			try{
				stmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		if(pstmt != null)
			try{
				pstmt.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		if(conn != null)
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();				
			}
		}
		
		public void delete(){
			conn=DBUtil.dbConnection();
			String sql = "delete ~~~~~~~~";
			
			try{
				stmt = conn.createStatement();
				//int a=stmt.excuteupdate(sql);
				System.out.println("delete 실행");
			}catch (SQLException e){
				e.printStackTrace();
			}finally{
				this.close();
			}
		}
		
		public void select(){
			conn=DBUtil.dbConnection();
			
			String sql="select * from member where mem_id =?";
			//String sql="select * from member";
			
			try{
				stmt = conn.createStatement();
				rs=stmt.executeQuery(sql);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "a001");
				//실행
				rs=pstmt.executeQuery();
				System.out.println("select 실행");
			
				} catch(SQLException e){
					e.printStackTrace();
					
				}finally{
					this.close();
				}
			}
		
		public static void main(String[] args){
			DBPro pro= new DBPro();
			
			while(true){
				System.out.println("\n 번호입력=========");
				Scanner sc=new Scanner(System.in);
				
				String a =sc.next();
				
				switch(a){
				case "1" :
					pro.select();
					break;
				case "2" :
					pro.delete();
				case "3" :
					System.out.println("종료");
					System.exit(0);
					break;
					
				}
			}
		}
		


}
