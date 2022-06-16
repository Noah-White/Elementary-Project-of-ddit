package Project_DTO;

public class recommendDTO {
	
	String rcm_book_star;
	String book_num;
	String rcm_book_ment;
	String mem_id;
	
	public recommendDTO(){}
	
	public recommendDTO(String rcm_book_star, String book_num,
			String rcm_book_ment, String mem_id) {
		super();
		this.rcm_book_star = rcm_book_star;
		this.book_num = book_num;
		this.rcm_book_ment = rcm_book_ment;
		this.mem_id = mem_id;
	}

	public String getRcm_book_star() {
		return rcm_book_star;
	}

	public void setRcm_book_star(String rcm_book_star) {
		this.rcm_book_star = rcm_book_star;
	}

	public String getBook_num() {
		return book_num;
	}

	public void setBook_num(String book_num) {
		this.book_num = book_num;
	}

	public String getRcm_book_ment() {
		return rcm_book_ment;
	}

	public void setRcm_book_ment(String rcm_book_ment) {
		this.rcm_book_ment = rcm_book_ment;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	
	
	

}
