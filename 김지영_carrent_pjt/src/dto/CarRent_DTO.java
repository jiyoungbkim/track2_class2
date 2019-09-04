package dto;

public class CarRent_DTO {
	String rent_id, car_id, car_name, member_id, driving_km, rent_start_date, rent_return_date;

	public CarRent_DTO() {
		super();
	}

	public CarRent_DTO(String rent_id, String car_id, String member_id, String driving_km, String rent_start_date,
			String rent_return_date) {
		super();
		this.rent_id = rent_id;
		this.car_id = car_id;
		this.member_id = member_id;
		this.driving_km = driving_km;
		this.rent_start_date = rent_start_date;
		this.rent_return_date = rent_return_date;
	}

	public CarRent_DTO(String rent_id, String car_id, String car_name, String member_id, String driving_km,
			String rent_start_date, String rent_return_date) {
		super();
		this.rent_id = rent_id;
		this.car_id = car_id;
		this.car_name = car_name;
		this.member_id = member_id;
		this.driving_km = driving_km;
		this.rent_start_date = rent_start_date;
		this.rent_return_date = rent_return_date;
	}


	public String getRent_id() {
		return rent_id;
	}

	public String getCar_id() {
		return car_id;
	}
	
	public String getCar_name() {
		return car_name;
	}

	public String getMember_id() {
		return member_id;
	}

	public String getDriving_km() {
		return driving_km;
	}

	public String getRent_start_date() {
		return rent_start_date;
	}

	public String getRent_return_date() {
		return rent_return_date;
	}
	
	
}
