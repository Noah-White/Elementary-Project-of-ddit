package code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import Project.DBUtil;

//static RentdateExtend rde =new RentdateExtend(); 선언 후 		rde.Extend(); 로 호출하여 사용
public class RentdateExtend {

	
	 //* 메인으로 테스트 할 때, 참조처리 'static void Extend(){'에 걸고, main 살려서 작동 test
	public static void main(String[] args) {
		 /*} 
		static void Extend(){
		*/
		      Connection conn = null; //연결객체
		      ResultSet rs = null;       // 결과보관객체(뷰, 커서)
		      PreparedStatement pstmt = null;
		      Scanner sc= new Scanner(System.in);
		      
		      try {
			    	  conn=DBUtil.dbConnection();
			      
				      StringBuilder sql=new StringBuilder();
		
				      sql.append(" UPDATE RENT SET RENT_EXP_DATE = RENT_EXP_DATE + 14 WHERE BOOK_NUM = ? AND   MEM_ID = ?");
				      
				      System.out.print("대출기간 연장 할 책 번호 입력 : ");
				      String bookNum=sc.nextLine();	 //책번호 받기 (automatically link with BookFind class)
				      
				      System.out.print("회원 아이디 입력 : ");
				      String memID=sc.nextLine();		//회원 아이디 받기 (automatically link with joowon's class)
				      
				      pstmt=conn.prepareStatement(sql.toString()); 
				      pstmt.setString(1, bookNum);	
				      pstmt.setString(2, memID);
				      
				      int res=pstmt.executeUpdate();
				      
				      			  
						      if(res==0){
						    	  //'업데이트에 성공한 행의 개수==0인경우'
						    	  System.out.println("기간연장에 실패하였습니다. 관리자에게 문의해주세요");
						      }else{
						    	  System.out.println("기간 연장이 완료되었습니다.");
						      }
		      	}catch(Exception e){
		      				e.printStackTrace();
		      	}finally{
				      		 try {
				      			 if(rs!=null)   rs.close();	     
				      			 if(pstmt!=null)   pstmt.close();            
				                 if(conn!=null)   conn.close();
				              }catch(Exception e) {
				                 e.printStackTrace();
				              }
//				      		 DBClose.close();
		      	}

		}
	
}

