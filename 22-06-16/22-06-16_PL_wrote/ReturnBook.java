//패널티 , 마일리지, 대출가능권수 수정, 연체여부확인

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement; //동적
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReturnBook {
   

   static void Return() {

      Connection conn = null; // 연결객체
      ResultSet rs = null;
      PreparedStatement pstmt = null;// 동적SQL명령객체
      
      
      Scanner sc = new Scanner(System.in);
      int input1, input2  = 0;

      try {
         conn = DBUtil.dbConnection();
         StringBuilder sql = new StringBuilder();

         // 가능한 구분위해 띄워쓸 것
         sql.append(" SELECT B.BOOK_NUM, B.BOOK_NM, TO_CHAR(RENT_EXP_DATE,'YYYY-MM-DD'), M.MEM_NM , M.MEM_ID ");
         sql.append(" FROM BOOK B , MEMBER M, RENT R ");
         sql.append(" WHERE M.MEM_ID=R.MEM_ID ");
         sql.append(" AND R.RETURN_STAT = '대출중' ");
         sql.append(" AND B.BOOK_NUM = R.BOOK_NUM");
         sql.append(" AND B.BOOK_NUM = ?");
         sql.append(" AND M.MEM_TEL_NUM = ?");

         System.out.println(" 도서번호 입력 : ");
         String gnum = sc.nextLine();

         System.out.println(" 이용자 전화번호 입력 : ");
         String gphone = sc.nextLine();

         pstmt = conn.prepareStatement(sql.toString());
         pstmt.setString(1, gnum);
         pstmt.setString(2, gphone);

         rs = pstmt.executeQuery();

         if (rs.next()){
            String bookNum = rs.getString(1);
            String bookNm = rs.getString(2);
            String expDate = rs.getString(3);
            String memName = rs.getString(4);
            String memId = rs.getString(5);

            System.out.println("------------------------");
            System.out.println(memName + " 회원님");
            System.out.println("' " + bookNm + " '");
            System.out.println("반납 예정일은 " + expDate + " 입니다.");
            System.out.println("\n반납하시겠습니까? 1 반납 2 종료");

            while (true) {
               input1 = Integer.parseInt(sc.nextLine());
               switch (input1) {
               case 1:
                  updDate(bookNum); //sysdate 찍어줌
                  updStat(bookNum); //대출중 -> 반납완료
                  System.out.println("반납이 완료되었습니다.\n");
                  System.out.print("리뷰작성(별점&감상평)하시겠습니까? 1 작성 2 종료 \n");
                  input2 = Integer.parseInt(sc.nextLine());
                     switch(input2){
                     case 1:
                        recom(bookNum, memId);
                        System.out.println("\n 등록이 완료되었습니다 !");
                        break;
                     case 2:
                        break;
                     }
                  System.out.println("이용해 주셔서 감사합니다.");
                  break;
               case 2:
                  System.out.println("종료되었습니다.");
                  break;
               }
               break;
            }

         } else {
            System.out.println("도서번호 또는 전화번호를 잘못 입력하셨습니다.");
         }

      } catch (SQLException e) {
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

   // main

   // RENT 테이블의 RETURN_DATE를 SYSDATE로 업데이트
   public static void updDate(String bookNum) {

	   Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Scanner sc = new Scanner(System.in);

      try {
         conn = DBUtil.dbConnection();

         String sql5 = " UPDATE RENT SET RETURN_DATE = SYSDATE WHERE BOOK_NUM = ? ";

         pstmt = conn.prepareStatement(sql5);
         pstmt.setString(1, bookNum);

         pstmt.executeUpdate();

      } catch (SQLException e) {
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

   // RENT테이블의 RETURN_STAT을 (대출중→반납완료)로 업데이트
   public static void updStat(String bookNum) {

      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Scanner sc = new Scanner(System.in);

      try {
         conn = DBUtil.dbConnection();

         String sql5 = " UPDATE RENT SET RETURN_STAT = '반납완료' WHERE BOOK_NUM = ? ";

         pstmt = conn.prepareStatement(sql5);
         pstmt.setString(1, bookNum);

         pstmt.executeUpdate();

      } catch (SQLException e) {
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

   // 반납한 도서 별점 , 도서평 작성
   public static void recom(String bookNum, String memId) {

      Connection conn = null;
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      Scanner sc = new Scanner(System.in);

      try {
         conn = DBUtil.dbConnection();

         String sql6 = " INSERT INTO RECOMMEND(RCM_BOOK_STAR, BOOK_NUM , RCM_BOOK_MENT, MEM_ID) VALUES (?,?,?,?) ";

         System.out.println(" ☆별점을 입력하세요 (1~10 사이 숫자로 입력하세요)☆ ");
         int star = sc.nextInt();
         sc.nextLine();
         
         System.out.println(" ★감상평을 입력하세요★ ");
         String ment = sc.nextLine();

         pstmt = conn.prepareStatement(sql6);
         pstmt.setInt(1, star);
         pstmt.setString(2, bookNum);
         pstmt.setString(3, ment);
         pstmt.setString(4, memId);

         pstmt.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      } catch (InputMismatchException e){
         System.out.println("1~10 사이 숫자로 입력하세요");
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

}// class