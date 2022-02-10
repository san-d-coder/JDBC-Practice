package com.sandcoder.jdbc.model;

public class Employee {
	private int empId;
	private String empName;
	private String empEmail;
	private long empPhone;
	public Employee(int empId, String empName, String empEmail, long empPhone) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empPhone = empPhone;
	}
	public int getEmpId() {
		return empId;
	}
	public String getEmpName() {
		return empName;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public long getEmpPhone() {
		return empPhone;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empEmail=" + empEmail + ", empPhone=" + empPhone
				+ "]";
	}
	
	
}
