package com.travel.mate.dto;

public class UserDetailDTO {
	private int userCode;
	private String name;
	private int age;
	private String sex;
	private String location;
	private float meanPoint;
	
	public UserDetailDTO(){
		
	}

	public UserDetailDTO(int userCode, String name, int age, String sex, String location, float meanPoint) {
		super();
		this.userCode = userCode;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.location = location;
		this.meanPoint = meanPoint;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getMeanPoint() {
		return meanPoint;
	}

	public void setMeanPoint(float meanPoint) {
		this.meanPoint = meanPoint;
	}

	@Override
	public String toString() {
		return "UserDetailDTO [userCode=" + userCode + ", name=" + name + ", age=" + age + ", sex=" + sex
				+ ", location=" + location + ", meanPoint=" + meanPoint + "]";
	}
	
	
}
