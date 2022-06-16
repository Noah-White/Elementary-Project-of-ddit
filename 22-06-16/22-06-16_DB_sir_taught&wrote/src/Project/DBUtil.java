package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	   static Connection conn;
	   
	   static {
	         try{
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            //System.out.println("클래스 로드 성공");
	            
	         }catch (ClassNotFoundException e){
	            e.printStackTrace();
	         }
	   }
	   public static Connection dbConnection(){
	      String driver = "oracle.jdbc.driver.OracleDriver";
	      String url= "jdbc:oracle:thin:@192.168.143.17:1521:xe";
	      String id="bhj";
	      String pass="java";
	      
	      try{
	         Class.forName(driver);
	         conn=DriverManager.getConnection(url, id, pass);
	         //System.out.println("연결성공");
	      }catch(ClassNotFoundException e){
	         e.printStackTrace();
	      }catch (SQLException e){
	         e.printStackTrace();
	      }
	         return conn;

	   }
	   
	   public static void close(ResultSet rs) throws SQLException{
		   if (rs != null)
               rs.close();
	   }
	   
	   public static void close(PreparedStatement pstmt) throws SQLException{
		   if (pstmt != null)
			   pstmt.close();
	   }
	   
	   public static void close(Connection conn) throws SQLException{
		   if (conn != null)
			   conn.close();
	   }
	}