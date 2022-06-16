import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Guide {
	
	Guide(){
		

		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null; //SQL명령객체



		try{
			conn = DBUtil.dbConnection();
			String sql = " SELECT * "
					   + " FROM guide ";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);



			while(rs.next()){
				String ut = rs.getString(1);
				String ud = rs.getString(2);
				String addr = rs.getString(3);
				String dir = rs.getString(4);
				String floor = rs.getString(5);
				String rent = rs.getString(6);

				System.out.println("이용시간안내"+ ut);
				System.out.println("휴관일"+ ud);
				System.out.println("도서관주소"+ addr);
				System.out.println("찾아오는길"+ dir);
				System.out.println("층수안내"+ floor);
				System.out.println("대출반납안내"+ rent);


			}
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DBClose.close();		
			}
	}
	
}
