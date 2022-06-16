package Project_DTO;

public class guideDTO {
	
	String use_time;
	String use_date;
	String addr;
	String direction;
	String floor_guide;
	String rent_return_guide;
	
	
	public guideDTO(){}
	
	public guideDTO(String use_time, String use_date, String addr,
			String direction, String floor_guide, String rent_return_guide) {
		super();
		this.use_time = use_time;
		this.use_date = use_date;
		this.addr = addr;
		this.direction = direction;
		this.floor_guide = floor_guide;
		this.rent_return_guide = rent_return_guide;
	}


	public String getUse_time() {
		return use_time;
	}


	public void setUse_time(String use_time) {
		this.use_time = use_time;
	}


	public String getUse_date() {
		return use_date;
	}


	public void setUse_date(String use_date) {
		this.use_date = use_date;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public String getFloor_guide() {
		return floor_guide;
	}


	public void setFloor_guide(String floor_guide) {
		this.floor_guide = floor_guide;
	}


	public String getRent_return_guide() {
		return rent_return_guide;
	}


	public void setRent_return_guide(String rent_return_guide) {
		this.rent_return_guide = rent_return_guide;
	}
	
	
	
	
	
	

}
