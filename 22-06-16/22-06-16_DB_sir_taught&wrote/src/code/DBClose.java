package code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBClose {

		static Connection conn;
		static Statement stmt;
		static ResultSet rs;
		static PreparedStatement pstmt;
		
		public static void close() {
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
}
