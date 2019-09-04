package dto;

public class CarInfo_DTO {
	String car_id, car_name, produce, produce_ym, status;
	int driving_total_km;
	
	public CarInfo_DTO() {
		super();
	}
	public CarInfo_DTO(String car_id, String car_name, String produce, String produce_ym,
			int driving_total_km, String status) {
		super();
		this.car_id = car_id;
		this.car_name = car_name;
		this.produce = produce;
		this.produce_ym = produce_ym;
		this.status = status;
		this.driving_total_km = driving_total_km;
	}
	public String getCar_id() {
		return car_id;
	}
	public String getCar_name() {
		return car_name;
	}
	public String getProduce() {
		return produce;
	}
	public String getProduce_ym() {
		return produce_ym;
	}
	public String getStatus() {
		return status;
	}
	public int getDriving_total_km() {
		return driving_total_km;
	}
	
	
}
