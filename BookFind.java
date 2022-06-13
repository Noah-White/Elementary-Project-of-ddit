package project.elementary.code;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class  BookFind {

	public static void main(String[] args) {
		/*
				}

	
	static void Guide(){*/
		Scanner sc = new Scanner(System.in);
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //192.168.143.17
		String user = "bhj";
		String password = "java";

		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null; //SQL명령객체



		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);

			String sql = " SELECT  B.BOOK_NUM ,   B.BOOK_NM ,   B.WRITER ,   B.BOOK_TYPE	,  B.BOOK_INTRO,   R.RCM_BOOK_STAR,   R.RCM_BOOK_MENT "
						+ " FROM BOOK  B, RECOMMEND R "
						+ " WHERE   1=1 " + " AND     B.BOOK_NUM = R.BOOK_NUM "
					    + " AND     ( B.BOOK_NM LIKE '%권%' "
						+" OR  B.WRITER LIKE '%권%' "
					    +" OR  B.BOOK_TYPE LIKE '%권%' ) ";
	
			
			/*
			String sql = " SELECT MEM_ID , MEM_NAME , MEM_MILEAGE "
					   + " FROM MEMBER "
					   + " WHERE SUBSTR(MEM_REGNO2,1,1) = '2' ";*/

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);


			System.out.print("도서번호 \t 도서명 \t 저자 \t도서유형 \t 도서소개 \t 추천도서별점 \t 추천도서설명");
			System.out.println();
			while(rs.next()){

				
				
				String bkNum = rs.getString(1);
				String bkName = rs.getString(2);
				String bkWrt = rs.getString(3);
				String bkType = rs.getString(4);
				String bkIntr = rs.getString(5);
				String bkStar = rs.getString(6);
				String bkRcmMnt = rs.getString(7);
				
				/*
				System.out.print("도서번호"+ bkNum);
				System.out.print("\t도서명"+ bkName);
				System.out.print("\t저자"+ bkWrt);
				System.out.print("\t도서유형"+ bkType);
				System.out.print("\t도서소개"+ bkIntr);
				System.out.print("\t추천도서별점"+ bkStar);
				System.out.print("\t추천도서설명"+ bkRcmMnt);
*/
				//System.out.print("도서번호 \t 도서명 \t 저자 \t도서유형 \t 도서소개 \t 추천도서별점 \t 추천도서별명");
				System.out.println(bkNum +"\t"+ bkName +"\t"+  bkWrt +"\t"+ bkType +"\t"+ bkIntr +"\t"+bkStar +"\t"+ bkRcmMnt);

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
	}
}
