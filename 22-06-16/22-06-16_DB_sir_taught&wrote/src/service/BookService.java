package service;

import Project_DTO.bookDTO;
import dao.BookDAO;

public class BookService {
	//싱글톤
	//Instance
    private static BookService instance = new BookService();
    //private construct
    private BookService() {}
    public static BookService getInstance() {
        return instance;
    }
    
    BookDAO bookDAO = BookDAO.getInstance();
    
    //책제목or저자or도서분류 명을 받아서 검색 후 bookDTO 객체로 리턴
    public bookDTO Find(String SearchName){
    	return this.bookDAO.Find(SearchName);
    }
}
