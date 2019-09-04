package dto;

public class Member_DTO {
	String id, name, dept, rank, address, reg_date;
	int age;
		
	public Member_DTO() {
		super();
	}
	public Member_DTO(String id, String name, int age, String dept, String rank, String address, String reg_date) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.dept = dept;
		this.rank = rank;
		this.address = address;
		this.reg_date = reg_date;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDept() {
		return dept;
	}
	public String getRank() {
		return rank;
	}
	public String getAddress() {
		return address;
	}
	public String getReg_date() {
		return reg_date;
	}
	public int getAge() {
		return age;
	}
}
