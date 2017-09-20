package com.swapnil.bean;

import java.math.BigDecimal;

import com.swapnil.annotation.Bean;
import com.swapnil.constant.DIType;

/**
 * @author Swapnil Bhosale
 * This is just a POJO class for Employee
 */
@Bean(name="emp",type=DIType.DIBySetter)
public class Employee {

	private Long empId;
	private String empName;
	private BigDecimal salary;
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [empId=");
		builder.append(empId);
		builder.append(", empName=");
		builder.append(empName);
		builder.append(", salary=");
		builder.append(salary);
		builder.append("]");
		return builder.toString();
	}

	public Employee(Long empId, String empName, BigDecimal salary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.salary = salary;
	}
	public Employee() {

	}




}
