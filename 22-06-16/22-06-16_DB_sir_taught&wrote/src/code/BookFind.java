package code;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

import Project.DBUtil;

//	static BookFind bf =new BookFind(); 선언 후 		bf.Find(); 로 호출하여 사용
public class  BookFind {
	/*
	 *메인으로 테스트 할 때, 참조처리 'static void Find(){'에 걸고, main 살려서 작동 test
	public static void main(String[] args) {
				}*/
	
	static void Find(){
		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		String SearchName = sc.next();
		//String 책제목 = 북넘버;
		//String 아이디 = id;


			try{
				conn=DBUtil.dbConnection();
				
				String sql = " SELECT  B.BOOK_NUM ,   B.BOOK_NM ,   B.WRITER ,   B.BOOK_TYPE	,  B.BOOK_INTRO,   R.RCM_BOOK_STAR,   R.RCM_BOOK_MENT "
							+ " FROM BOOK  B, RECOMMEND R "
							+ " WHERE   1=1 " + " AND     B.BOOK_NUM = R.BOOK_NUM "
						    + " AND     ( B.BOOK_NM LIKE '%" + SearchName +"%' "
							+" OR  B.WRITER LIKE '%" +SearchName  +"%' "
						    +" OR  B.BOOK_TYPE LIKE '%" + SearchName +"%' ) ";
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
	
	
				System.out.print("도서번호 \t 도서명 \t 저자 \t도서유형 \t 도서소개 \t 추천도서별점 \t 추천도서설명");
				System.out.println();
		
			
					while(rs.next()){
		
						
						
						String bkNum = rs.getString(1);					//도서번호
						String bkName = rs.getString(2); 					//도서명
						String bkWrt = rs.getString(3); 					//저자
						String bkType = rs.getString(4); 					//도서유형
						String bkIntr = rs.getString(5); 					//도서소개
						String bkStar = rs.getString(6); 					//추천도서별점
						String bkRcmMnt = rs.getString(7); 				//추천도서설명
		
						//System.out.print("도서번호 \t 도서명 \t 저자 \t도서유형 \t 도서소개 \t 추천도서별점 \t 추천도서별명");
						System.out.println(bkNum +"\t"+ bkName +"\t"+  bkWrt +"\t"+ bkType +"\t"+ bkIntr +"\t"+bkStar +"\t"+ bkRcmMnt);

						}
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
//					DBClose.close();
			}
			
		
		/*
			if(대출상태equals 대출중)
				System.out.println(" 이 도서는 현재 대출이 불가능합니다. ");
				System.out.println("┌────────────────────────────────────────────-┐");
				System.out.println("│                                               0. 뒤로가기                  │");
				System.out.println("└─────────────────────────────────────────────┘");
		
		
			else
				System.out.println("┌────────────────────────────────────────────-┐");
				System.out.println("│         1.대출                      0. 뒤로가기                  │");
				System.out.println("└─────────────────────────────────────────────┘");
			
		
					// pstmt=conn.prepareStatement(sql.toString());
					//int res=pstmt.executeUpdate();*/
	}
}
