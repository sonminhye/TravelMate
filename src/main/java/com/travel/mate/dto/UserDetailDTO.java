package com.travel.mate.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailDTO {
	private int userCode;
	@NotNull
	@Size(min=1)
	private String name;

	@Max(100)
	@Min(1)
	private int age;
	
	@NotNull
	@Size(min=1) 
	private String sex;
	
	@NotNull
	@Size(min=1) 
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
