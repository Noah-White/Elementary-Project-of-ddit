package Project_DTO;

import java.util.List;

public class bookDTO {
	
	String book_num;
	String book_nm;
	String writer;
	String book_type;
	String book_intro;
	int book_price;
	String etc;
	List<recommendDTO> rDTO;	//별점(1:다)
	
	public bookDTO(){}
	
	
	public bookDTO(String book_num, String book_nm, String writer,
			String book_type, String book_intro, int book_price, String etc) {
		super();
		this.book_num = book_num;
		this.book_nm = book_nm;
		this.writer = writer;
		this.book_type = book_type;
		this.book_intro = book_intro;
		this.book_price = book_price;
		this.etc = etc;
	}


	public String getBook_num() {
		return book_num;
	}


	public void setBook_num(String book_num) {
		this.book_num = book_num;
	}


	public String getBook_nm() {
		return book_nm;
	}


	public void setBook_nm(String book_nm) {
		this.book_nm = book_nm;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getBook_type() {
		return book_type;
	}


	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}


	public String getBook_intro() {
		return book_intro;
	}


	public void setBook_intro(String book_intro) {
		this.book_intro = book_intro;
	}


	public int getBook_price() {
		return book_price;
	}


	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}


	public String getEtc() {
		return etc;
	}


	public void setEtc(String etc) {
		this.etc = etc;
	}


	public List<recommendDTO> getrDTO() {
		return rDTO;
	}


	public void setrDTO(List<recommendDTO> rDTO) {
		this.rDTO = rDTO;
	}


	@Override
	public String toString() {
		return "bookDTO [book_num=" + book_num + ", book_nm=" + book_nm
				+ ", writer=" + writer + ", book_type=" + book_type
				+ ", book_intro=" + book_intro + ", book_price=" + book_price
				+ ", etc=" + etc + ", rDTO=" + rDTO + "]";
	}


	
	
	

	
	

}
