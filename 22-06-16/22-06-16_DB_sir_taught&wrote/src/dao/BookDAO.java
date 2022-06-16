package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Project.DBUtil;
import Project_DTO.bookDTO;
import Project_DTO.recommendDTO;

public class BookDAO {
	//싱글톤
	//Instance
    private static BookDAO instance = new BookDAO();
    //private construct
    private BookDAO() {}
    public static BookDAO getInstance() {
        return instance;
    }
    
    //책제목or저자or도서분류 명을 받아서 검색 후 bookDTO 객체로 리턴
    public bookDTO Find(String SearchName){
    	bookDTO dto = new bookDTO();

		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		//String 책제목 = 북넘버;
		//String 아이디 = id;


			try{
				conn = DBUtil.dbConnection();
				
				String sql = " SELECT  B.BOOK_NUM ,   B.BOOK_NM ,   B.WRITER ,   B.BOOK_TYPE	,  B.BOOK_INTRO "
							+ " FROM BOOK  B "
							+ " WHERE   1=1 "
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
		
						//System.out.print("도서번호 \t 도서명 \t 저자 \t도서유형 \t 도서소개 \t 추천도서별점 \t 추천도서별명");
//						//System.out.println(bkNum +"\t"+ bkName +"\t"+  bkWrt +"\t"+ bkType +"\t"+ bkIntr +"\t"+bkStar +"\t"+ bkRcmMnt);
							dto.setBook_num(bkNum);
							dto.setBook_nm(bkName);
							dto.setWriter(bkWrt);
							dto.setBook_type(bkType);
							dto.setBook_intro(bkIntr);
							//책번호를 던지면 별점이 list로 채워짐
							dto.setrDTO(FindStar(bkNum));
						}
					
					return dto;
				}catch(SQLException e){
					e.printStackTrace();
					return dto;
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
    
  //책제목or저자or도서분류 명을 받아서 검색 후 bookDTO 객체로 리턴
    public List<recommendDTO> FindStar(String bookNum){
    	List<recommendDTO> list = new ArrayList<recommendDTO>();
    	
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		//String 책제목 = 북넘버;
		//String 아이디 = id;


			try{
				conn = DBUtil.dbConnection();
				
				String sql = " SELECT RCM_BOOK_STAR,BOOK_NUM,RCM_BOOK_MENT,MEM_ID " 
						+ " FROM   RECOMMEND "
						+ " WHERE BOOK_NUM = '" + bookNum + "'";
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
	
	
//				System.out.print("도서번호 \t 도서명 \t 저자 \t도서유형 \t 도서소개 \t 추천도서별점 \t 추천도서설명");
//				System.out.println();
				
			
					while(rs.next()){
		
						recommendDTO rDTO = new recommendDTO();
						
						rDTO.setRcm_book_star(rs.getString(1));
						rDTO.setBook_num(rs.getString(2));
						rDTO.setRcm_book_ment(rs.getString(3));
						rDTO.setMem_id(rs.getString(4));
						
						list.add(rDTO);
					}
					
					return list;
				}catch(SQLException e){
					e.printStackTrace();
					return list;
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
