import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;
import java.sql.PreparedStatement;

public class LoginPage {
	static String DB_grade;
	static String DB_id;
	static String DB_nm;
	static String DB_tel;
	static String DB_addr;
	static int DB_mil;
	static String logId;
	static Scanner sc = new Scanner(System.in);
	static ArrayList<String> rentList = new ArrayList<String>(4);
	static String DB_rentNM;
	static String DB_rentNum;
	static String DB_rentDT;
	static String DB_returnDT;
	
	// static String DB_bookNum;

	LoginPage() {

		String admin = "admin";
		String logPw; // 입력받은 id pw

		System.out.println("로그인id입력");
		logId = sc.next();

		// 관리자계정 접근완료
		if (logId.equals(admin)) {

		} else { // 일반사용자
			getMy(logId);
			loginStart(); //회원로그인후 메인페이지
		}

		System.out.println("로그인pw입력");
		logPw = sc.next();

	}

	static void getMy(String logId) {
		
		String sql = "select * from member where mem_id = '" + logId + "'";// login
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null; // SQL명령객체

		try {

			conn = DBUtil.dbConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				DB_grade = rs.getString("mem_grade");
				DB_id = rs.getString("mem_id");
				DB_nm = rs.getString("mem_nm");
				DB_tel = rs.getString("mem_tel_num");
				DB_addr = rs.getString("mem_addr");
				DB_mil = rs.getInt("mileage");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void loginStart() {

		while (true) {
			int input;
			System.out.println("[로그인 페이지]\tID : " + DB_id + "  등급 : "
					+ DB_grade);
			System.out
					.println("┌───────────────────────────────────────────────────┐");
			System.out.println("│  1.정보변경 2.회원탈퇴 3.대출정보 4.도서조회 5.로그아웃  │");
			System.out
					.println("└───────────────────────────────────────────────────┘");
			input = sc.nextInt();

			if (input == 1) {
				System.out.println("[정보변경]");
				
				profile();
			} else if (input == 2) {
				System.out.println("[회원탈퇴]");
				// DropMembership drop = new DropMembership();
				// drop.main(null);
			} else if (input == 3) {
				System.out.println("[대출정보]");
				getRent();
			} else if (input == 4) {
				System.out.println("[로그아웃]");
				return;
			} else
				System.out.println("[잘못된입력]\n");
		}

	}

	static void profile() {
		
		while (true) {
			getMy(logId);
			int input;
			System.out.println("┌───────────────────────┐");
			System.out.println("│ID : " + DB_id + "\t\t│" + "\n│이름 : " + DB_nm
					+ "\t\t│" + "\n│1.전화번호 : " + DB_tel + "\t│" + "\n│2.주소 : "
					+ DB_addr + "\t│" + "\n│마일리지 : " + DB_mil + "\t\t│"
					+ "\n│회원등급 : " + DB_grade + "\t\t│");
			System.out.println("└───────────────────────┘");
			System.out.println("=========================================");
			System.out.println("|         수정할 항목을 선택하세요 \t\t|");
			System.out.println("|           1. 회원 전화번호\t\t|");
			System.out.println("|           2. 회원 주소\t\t\t|");
			System.out.println("|           3. 돌아 가기\t\t\t|");
			System.out.println("=========================================");
			System.out.print("          > ");

			input = sc.nextInt();
			if (input == 1) {
				System.out.println("[전화번호 수정]");
				telSet();
			} else if (input == 2) {
				System.out.println("[주소 수정]");
				addrSet();
			} else if (input == 3) {
				System.out.println("[돌아가기]");
				break;
			} else
				System.out.println("[잘못된 입력]\n");
		}
	}

	static void getRent() {// 대출중인 도서목록
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null; // SQL명령객체

		int input;
		rentList.clear();
		String sql = "select * from member m, rent r, book b "
				+ " where m.mem_id = 'a001' " // 아이디 입력받기 수정
				+ " and r.return_stat='대출중'" + " and r.book_num = b.book_num "
				+ " and m.mem_id = r.mem_id";
		try {

			conn = DBUtil.dbConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			System.out.println("대출중인 도서목록 ");
			System.out
					.println("────────────────────────────────────────────────────────────");
			while (rs.next()) {
				DB_rentNM = rs.getString("book_nm");
				DB_rentNum = rs.getString("book_num");
				DB_rentDT = rs.getString("rent_date");
				DB_returnDT = rs.getString("return_date");

				System.out.println(" -" + DB_rentNum + " | " + DB_rentNM
						+ " | " + DB_rentDT);

				rentList.add(rs.getString("book_num"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println();
			System.out.println(" 1.반납 2.돌아가기" + "\t\t");
			System.out
					.println("────────────────────────────────────────────────────────────");
			input = sc.nextInt();
			if (input == 1) {
				System.out.println("[반납]");
				selBook();
			} else if (input == 2) {
				break;
			} else {
			}
			System.out.println("[잘못된입력]\n");
		}
	}

	static void telSet() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String tel;
		System.out.println("전화번호 입력 (예시)000-0000-0000");
		String sql = "update member set mem_tel_num=? where mem_id=?";
		tel = sc.next();
		try {
			conn = DBUtil.dbConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tel);
			pstmt.setString(2, DB_id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	static void addrSet() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String addr;
		System.out.println("주소 입력 (예시)000시 000구 000동");
		String sql = "update member set mem_addr=? where mem_id=?";
		sc.nextLine();
		addr = sc.nextLine();

		try {
			conn = DBUtil.dbConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, addr);
			pstmt.setString(2, DB_id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	static void selBook() {// 반납

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int input;
		while (true) {
			try {

				conn = DBUtil.dbConnection();
				System.out.println("책번호입력");
				String bookNum = sc.next();
				if (rentList.get(0).equals(bookNum)
						|| rentList.get(1).equals(bookNum)
						|| rentList.get(2).equals(bookNum)
						|| rentList.get(3).equals(bookNum)) {
					System.out.println("------------------------");
					System.out.println(DB_nm + " 회원님");
					System.out.println("' " + DB_rentNM + " '");
					System.out.println("반납 예정일은 " + DB_rentDT + " 입니다.");
					System.out.println("\n반납하시겠습니까? 1 반납 2 종료");

					input = sc.nextInt();
					if (input == 1) {
						System.out.println("[반납완료]");
						try {
							String sql = " update rent set return_stat = '반납완료',  return_date=SYSDATE where mem_id = ? and book_num = ? ";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, DB_id);
							pstmt.setString(2, bookNum);
							pstmt.executeUpdate();
							loginStart();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (input == 2) {
						System.out.println("[종료]");
						loginStart();
					} else {
						System.out.println("잘못된 입력");
					}

				} else {
					System.out.println("맞는책정보 없음, 책번호 확인해주세요");
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			} finally {
				try {
					if (pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
