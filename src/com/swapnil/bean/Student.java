package com.swapnil.bean;

import com.swapnil.annotation.Bean;

@Bean(name="student")
public class Student {

	private Long rollNo;
	private String name;
	public Long getRollNo() {
		return rollNo;
	}
	public void setRollNo(Long rollNo) {
		this.rollNo = rollNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Student(Long rollNo, String name) {
		super();
		this.rollNo = rollNo;
		this.name = name;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [rollNo=");
		builder.append(rollNo);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}