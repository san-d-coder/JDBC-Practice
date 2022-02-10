package com.sandcoder.jdbc.practice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sandcoder.jdbc.model.Employee;
import com.sandcoder.jdbc.utils.MessageSource;
import com.sandcoder.jdbc.utils.QuerySource;

public class Repository {

	private static Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String url = null;
	private String username = null;
	private String password = null;

	public Repository() {
		this.url = System.getenv("url");
		this.username = System.getenv("username");
		this.password = System.getenv("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String createEmployee(Employee employee) {
		String returnStatement = "";
		try {
			this.preparedStatement = Repository.connection.prepareStatement(QuerySource.createEmployee);
			this.preparedStatement.setInt(1, employee.getEmpId());
			this.preparedStatement.setString(2, employee.getEmpName());
			this.preparedStatement.setString(3, employee.getEmpEmail());
			this.preparedStatement.setLong(4, employee.getEmpPhone());

			this.preparedStatement.execute();

			returnStatement = MessageSource.insertSuccess;

		} catch (Exception exception) {
			returnStatement = MessageSource.insertFailure;
			System.out.println(exception.getMessage());
		}
		return returnStatement;
	}

	public List<Employee> getEmployees() {
		List<Employee> employeeList = new ArrayList<>();
		try {
			this.statement = Repository.connection.createStatement();
			this.resultSet = this.statement.executeQuery(QuerySource.getEmployees);
			while (this.resultSet.next() != false) {
				employeeList.add(new Employee(this.resultSet.getInt(1), this.resultSet.getString(2),
						this.resultSet.getString(3), this.resultSet.getLong(4)));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return employeeList;
	}

	public Employee updateEmployeeEmail(String employeeEmail, int employeeId) {
		try {
			this.preparedStatement = Repository.connection.prepareStatement(QuerySource.updateEmployee);
			this.preparedStatement.setString(1, employeeEmail);
			this.preparedStatement.setInt(2, employeeId);
			this.preparedStatement.execute();
			return getEmployee(employeeId);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Employee getEmployee(int employeeId) {
		try {
			this.preparedStatement = Repository.connection.prepareStatement(QuerySource.getEmployee);
			this.preparedStatement.setInt(1, employeeId);
			this.resultSet = this.preparedStatement.executeQuery();
			while (resultSet.next() != false)
				return new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getLong(4));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void deleteEmployee(int employeeId) {
		try {
			this.preparedStatement = Repository.connection.prepareStatement(QuerySource.deleteEmployee);
			this.preparedStatement.setInt(1, employeeId);
			this.preparedStatement.execute();
			System.out.println("Employee Deleted!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(MessageSource.deleteFailure);
		}
	}

}
