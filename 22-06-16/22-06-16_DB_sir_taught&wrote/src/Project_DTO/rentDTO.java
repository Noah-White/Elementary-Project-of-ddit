package Project_DTO;

public class rentDTO {
	
	String rent_num;
	String book_num;
	String rent_date;
	String rent_exp_date;
	String return_stat;
	String return_date; 
	String mem_id;
	
	public rentDTO(){}

	public rentDTO(String rent_num, String book_num, String rent_date,
			String rent_exp_date, String return_stat, String return_date,
			String mem_id) {
		super();
		this.rent_num = rent_num;
		this.book_num = book_num;
		this.rent_date = rent_date;
		this.rent_exp_date = rent_exp_date;
		this.return_stat = return_stat;
		this.return_date = return_date;
		this.mem_id = mem_id;
	}

	public String getRent_num() {
		return rent_num;
	}

	public void setRent_num(String rent_num) {
		this.rent_num = rent_num;
	}

	public String getBook_num() {
		return book_num;
	}

	public void setBook_num(String book_num) {
		this.book_num = book_num;
	}

	public String getRent_date() {
		return rent_date;
	}

	public void setRent_date(String rent_date) {
		this.rent_date = rent_date;
	}

	public String getRent_exp_date() {
		return rent_exp_date;
	}

	public void setRent_exp_date(String rent_exp_date) {
		this.rent_exp_date = rent_exp_date;
	}

	public String getReturn_stat() {
		return return_stat;
	}

	public void setReturn_stat(String return_stat) {
		this.return_stat = return_stat;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	
	

}
