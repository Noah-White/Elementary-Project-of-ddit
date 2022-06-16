package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginP {
   static String DB_id;
   static String DB_nm;
   static String DB_tel;
   static String DB_addr;
   static int DB_mil;
   static String DB_grade;
   static String DB_rentNM;
   static String DB_rentNum;
   static String DB_rentDT;
   static String DB_returnDT;
   static String DB_bookNum;

   static Connection conn = null; // 연결객체
   static ResultSet rs = null; // 결과보관객체(뷰, 커서)
   static Statement stmt = null; // 정적sql명령 객체
   static PreparedStatement pstmt = null; // 동적sql명령 객체
   static Scanner sc = new Scanner(System.in);
   static ArrayList<String> rentList = new ArrayList<String>(4);

   public static void main(String[] args) {
	   //Youn_로그인 _DBUtil DAO로 수정
      String driver = "oracle.jdbc.driver.OracleDriver";
      String url = "jdbc:oracle:thin:@192.168.143.17:1521:xe"; // localhost
      String user = "bhj";
      String password = "java";

      try {
         Class.forName(driver); // 1. 드라이버 파일을 찾는다
         conn = DriverManager.getConnection(url, user, password); // 2. 연결설정, 연결권한 생김

         getMy();

         loginStart();

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

   }

   static void loginStart() {
      while (true) {
         int input;
         System.out.println("[로그인 페이지]\tID : " + DB_id + "  등급 : " + DB_grade);
         System.out.println("┌─────────────────────────────────────────────┐");
         System.out.println("│1.정보변경 2.회원탈퇴 3.대출정보 4.도서조회 5.로그아웃    │");
         System.out.println("└─────────────────────────────────────────────┘");
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

   static void getMy() {
      String sql = "select * from member where mem_id = '" + "a001'";// login 한 아이디 받아서
      ResultSet rs = null;
      try {
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

   static void getRent() {// 대출중인 도서목록
      int input;
      rentList.clear();
      String sql = "select * from member m, rent r, book b where m.mem_id = 'a001' and r.return_stat='대출중' and r.book_num = b.book_num and m.mem_id = r.mem_id";
      try {
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);

         System.out.println("대출중인 도서목록 ");
         System.out.println("────────────────────────────────────────────────────────────");
         while (rs.next()) {
            DB_rentNM = rs.getString("book_nm");
            DB_rentNum = rs.getString("book_num");
            DB_rentDT = rs.getString("rent_date");
            DB_returnDT = rs.getString("return_date");

            System.out.println(" -" + DB_rentNum + " | " + DB_rentNM + " | " + DB_rentDT);

            rentList.add(rs.getString("book_num"));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      while (true) {
         Scanner sc = new Scanner(System.in);
         System.out.println();
         System.out.println(" 1.반납 2.돌아가기" + "\t\t");
         System.out.println("────────────────────────────────────────────────────────────");
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

   static void selBook() {// 반납
      int input;

      while (true) {
         try {
            String sql = "update rent set return_stat = '반납완료',return_date=SYSDATE where mem_id = ? and book_num = ?";
            System.out.println("책번호입력");
            String bookNum = sc.next();
            if (rentList.get(0).equals(bookNum) || rentList.get(1).equals(bookNum)
                  || rentList.get(2).equals(bookNum) || rentList.get(3).equals(bookNum)) {
               System.out.println("------------------------");
               System.out.println(DB_nm + " 회원님");
               System.out.println("' " + DB_rentNM + " '");
               System.out.println("반납 예정일은 " + DB_rentDT + " 입니다.");
               System.out.println("\n반납하시겠습니까? 1 반납 2 종료");

               input = sc.nextInt();

               if (input == 1) {
                  System.out.println("[반납완료]");
                  try {
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setString(1, DB_id);
                     pstmt.setString(2, bookNum);
                     pstmt.executeUpdate();
                  } catch (Exception e) {
                     e.printStackTrace();
                  }
               } else if (input == 2) {
                  System.out.println("[종료]");
                  break;
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

   // 반납한 도서 별점 , 도서평 작성
   static void profile() {
      while (true) {
         int input;
         System.out.println("┌───────────────────────┐");
         System.out.println("│ID : " + DB_id + "\t\t│" + "\n│이름 : " + DB_nm + "\t\t│" + "\n│1.전화번호 : " + DB_tel
               + "\t│" + "\n│2.주소 : " + DB_addr + "\t│" + "\n│마일리지 : " + DB_mil + "\t\t│" + "\n│회원등급 : " + DB_grade
               + "\t\t│");
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

   static void telSet() {
      String tel;
      System.out.println("전화번호 입력 (예시)000-0000-0000");
      String sql = "update member set mem_tel_num=? where mem_id=?";
      tel = sc.next();
      try {
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
      String addr;
      System.out.println("주소 입력 (예시)000시 000구 000동");
      String sql = "update member set mem_addr=? where mem_id=?";
      sc.nextLine();
      addr = sc.nextLine();

      try {
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

}