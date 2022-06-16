import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Membership {
         
   // 나중에 기능 점검할 때, main 살려서 확인.
   //public static void main(String[] args) {
      
   static void membership(){
         
         //DB연동객체생성
         Connection conn = null; 
         ResultSet rs = null; 
         Statement stmt = null; 
         PreparedStatement pstmt = null;
         
         Scanner sc = new Scanner(System.in);
            
         StringBuilder sql = new StringBuilder();
         String id ;
            
         try {
                  conn=DBUtil.dbConnection();
                  
                  sql.append(" insert into member(mem_id,mem_pw,mem_nm,mem_tel_num,regno1,regno2,mem_addr,mileage,mem_grade,mem_delete"+ ") ");
                  sql.append(" values( ?, ?, ?, ?, ?, ?, ?,?,?,0) ");
                  
         
                  System.out.println("──────────회원가입──────────");
            
                //아이디 처음 입력
                  System.out.print("아이디 입력 : ");
                  
                  id=sc.next();
                  while(true) {
                     
                     while(true){
                        if(memID(id)==true)
                        {
                           System.out.print("아이디 입력 : ");
                             
                             id=sc.next();
                        }else{
                           break;
                           //아이디 중복 없을 시 pw 입력으로 돌아가기 (while문 탈출)
                        }              
                     }

                  
               //비밀번호 입력
              String pw;
            String pw2;
               do {
                           System.out.print("비밀번호 입력 : ");
                           pw = sc.next();
                           System.out.print("비밀번호 재입력 : ");
                           pw2 = sc.next();
                           
               //개인정보 입력            
                  }while(!pw.equals(pw2));
               
                           System.out.print("이름 입력 : ");
                           String name = sc.next();
                           
                           System.out.print("전화번호 입력 : ");
                           String tel = sc.next();
                           
                           System.out.print("주민번호 앞자리 입력 : ");
                           String reg1 = sc.next();
                           
                           System.out.print("주민번호 뒷자리 입력 : ");
                           String reg2 = sc.next();
                           
                           sc.nextLine();
                           
                           System.out.print("주소입력 : ");
                           String add = sc.nextLine();
                           
                           
            
            
                           pstmt=conn.prepareStatement(sql.toString());
                           pstmt.setString(1, id);
                           pstmt.setString(2,pw );
                           pstmt.setString(3,name );
                           pstmt.setString(4,tel );
                           pstmt.setString(5,reg1);
                           pstmt.setString(6,reg2 );
                           pstmt.setString(7,add );
                           pstmt.setString(8,"0" );
                           pstmt.setString(9,"일반");
                           
                           int res = pstmt.executeUpdate();
                           
                  if(res==0) {
                     System.out.println("입력실패");
                  }else {
                     System.out.println("회원가입성공");
                    
                     
                     break;
                  }
               }
            }catch(Exception e) {
                  e.printStackTrace();
                  //DB연동소스 닫아주기
            }finally {
                  DBClose.close();
               }
            
         }

      

            //ID 중복여부 판단하는 메서드. 중복시 true 반환하여, 아이디 처음입력시 반복문 실행
            //아이디 중복 아닐시 false 반환하여, 아이디 처음입력시 반복문 탈출 후 이후 프로세스 실행
         public static boolean memID(String id) throws ClassNotFoundException  {
           boolean bool=false ;
            Connection conn = null; 
            ResultSet rs = null;  
            PreparedStatement pstmt = null;
            StringBuilder sql = new StringBuilder();
      
            
            try {
               conn=DBUtil.dbConnection();;
      
                sql.append("select mem_id from member where mem_id = ? ");   
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, id);
      
                rs = pstmt.executeQuery();
          
                if(rs.next()){
                   
                   System.out.println("해당 아이디는 사용할 수 없습니다.");
                   System.out.println("아이디를 다시 입력하세요.");
                   bool = true;
                      //return , boolean, true/false에 따라서.. 
                }else{
                   System.out.println("해당 아이디로 사용하실 수 있습니다.");
                   bool =  false;
                }
             } catch (SQLException e) {
             e.printStackTrace();
      
             }
          return bool;
         }
      }
        