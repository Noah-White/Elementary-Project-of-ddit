import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class FirstProject {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);		
		
		
			
		int input;
		while(true){
			System.out.println("┌────────────────────────────────────────────-┐");
			System.out.println("│1.도서관안내 2.회원가입 3.로그인  4.책조회 5.책반납 0. 프로그램종료  │");
			System.out.println("└─────────────────────────────────────────────┘");

			input = Integer.parseInt(sc.nextLine());

			if(input == 1){
				System.out.println("도서관안내");
				Guide();
			}else if(input ==2){
				System.out.println("회원가입");
				Join();
			}else if(input ==3){
				System.out.println("로그인");
				Login();
			}else if(input ==4){
				System.out.println("책조회");
				Search();
			}else if(input ==5){
				System.out.println("책반납");
				ReturnBook();
				//평점추가
			}else if(input ==0){
				System.out.println("프로그램종료");
				break;
			}else
				System.out.println("잘못된입력");
		}
	}

	static void Join(){
		Membership mem = new Membership();
		mem.membership();
	}

	static void Login() {
		LoginPage lg = new LoginPage();
	}

	static void Search(){
		System.out.println("책제목or저자or도서분류");
		BookFind bf = new BookFind();
		bf.Find();
	}
	static void ReturnBook(){
		ReturnBook rb = new ReturnBook();
		rb.Return();
	}

	static void Guide(){
		Guide a = new Guide();
	}
}
