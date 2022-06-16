import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

//	static BookFind bf =new BookFind(); 선언 후 		bf.Find(); 로 호출하여 사용
public class BookFind {
	/*
	 * 메인으로 테스트 할 때, 참조처리 'static void Find(){'에 걸고, main 살려서 작동 test public
	 * static void main(String[] args) { }
	 */

	static void Find() {
		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		String SearchName = sc.next();
		// String 책제목 = 북넘버;
		// String 아이디 = id;

		try {
			conn = DBUtil.dbConnection();

			String sql = " SELECT  B.BOOK_NUM ,   B.BOOK_NM ,   B.WRITER ,   B.BOOK_TYPE ,   B.BOOK_INTRO ,   avg(R.RCM_BOOK_STAR) as avg_star "
                    +" FROM BOOK  B, RECOMMEND R "
                    +"WHERE   1=1   AND     B.BOOK_NUM = R.BOOK_NUM     AND     ( B.BOOK_NM LIKE '%" +SearchName+ "%'  OR  B.WRITER LIKE '%"+SearchName 
                    +"%' OR  B.BOOK_TYPE LIKE '%"+SearchName +"%' )  group by B.BOOK_NUM ,   B.BOOK_NM,   B.WRITER ,   B.BOOK_TYPE ,   B.BOOK_INTRO ";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String bkNum = rs.getString(1); // 도서번호
				String bkName = rs.getString(2); // 도서명
				String bkWrt = rs.getString(3); // 저자
				String bkType = rs.getString(4); // 도서유형
				String bkIntr = rs.getString(5); // 도서소개
				String bkavgstar = rs.getString(6);
				
				System.out.println("┌────────────────────────────────────────────-┐");
				System.out.println("│ 도서번호 : "+ bkNum);
				System.out.println("│ 도서명 : "+ bkName);
				System.out.println("│ 저자 : "+ bkWrt);
				System.out.println("│ 도서유형 : "+ bkType);
				System.out.println("│ 도서소개 : "+ bkIntr);
				System.out.println("│ 도서평균별점 : "+ bkavgstar);
				System.out.println("└─────────────────────────────────────────────┘");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close();

		}

	}
}
