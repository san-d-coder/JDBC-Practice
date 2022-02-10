package com.sandcoder.jdbc.utils;

public interface QuerySource {
	String createEmployee = "insert into employee values(?,?,?,?)";
	String getEmployees = "select * from employee";
	String getEmployee = "select * from employee where emp_id = ?";
	String updateEmployee = "update employee set emp_email = ? where emp_id = ?";
	String deleteEmployee = "delete from employee where emp_id = ?";
}
