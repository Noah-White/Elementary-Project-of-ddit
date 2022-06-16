package Project_DTO;

public class memberDTO {
	
	String mem_id;
	String mem_pw;
	String mem_nm;
	String mem_tel_num;
	int regno1;
	int rengno2;
	String mem_addr;
	int mileage;
	String mem_grade;
	String mem_delete;
	
	public memberDTO(){}

	public memberDTO(String mem_id, String mem_pw, String mem_nm,
			String mem_tel_num, int regno1, int rengno2, String mem_addr,
			int mileage, String mem_grade, String mem_delete) {
		super();
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_nm = mem_nm;
		this.mem_tel_num = mem_tel_num;
		this.regno1 = regno1;
		this.rengno2 = rengno2;
		this.mem_addr = mem_addr;
		this.mileage = mileage;
		this.mem_grade = mem_grade;
		this.mem_delete = mem_delete;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_nm() {
		return mem_nm;
	}

	public void setMem_nm(String mem_nm) {
		this.mem_nm = mem_nm;
	}

	public String getMem_tel_num() {
		return mem_tel_num;
	}

	public void setMem_tel_num(String mem_tel_num) {
		this.mem_tel_num = mem_tel_num;
	}

	public int getRegno1() {
		return regno1;
	}

	public void setRegno1(int regno1) {
		this.regno1 = regno1;
	}

	public int getRengno2() {
		return rengno2;
	}

	public void setRengno2(int rengno2) {
		this.rengno2 = rengno2;
	}

	public String getMem_addr() {
		return mem_addr;
	}

	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getMem_grade() {
		return mem_grade;
	}

	public void setMem_grade(String mem_grade) {
		this.mem_grade = mem_grade;
	}

	public String getMem_delete() {
		return mem_delete;
	}

	public void setMem_delete(String mem_delete) {
		this.mem_delete = mem_delete;
	}

	@Override
	public String toString() {
		return "memberDTO [mem_id=" + mem_id + ", mem_pw=" + mem_pw
				+ ", mem_nm=" + mem_nm + ", mem_tel_num=" + mem_tel_num
				+ ", regno1=" + regno1 + ", rengno2=" + rengno2 + ", mem_addr="
				+ mem_addr + ", mileage=" + mileage + ", mem_grade="
				+ mem_grade + ", mem_delete=" + mem_delete + "]";
	}
	
	
	

}
