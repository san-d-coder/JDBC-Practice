package com.sandcoder.jdbc.practice;

import com.sandcoder.jdbc.model.Employee;
import com.sandcoder.jdbc.practice.repository.Repository;

public class Controller {

	public static void main(String[] args) {
		Repository repository = new Repository();
		//System.out.println(repository.createEmployee(new Employee(1, "Sandeep Ghosh", "sandeep.ghosh@email.com", 8350083028L)));
		//System.out.println(repository.createEmployee(new Employee(2, "Rahul Ghosh", "rahul.ghosh@email.com", 8350083027L)));
		//System.out.println(repository.createEmployee(new Employee(3, "Nilanjana Ghosh", "nilanjana.ghosh@email.com", 8350083026L)));
		//System.out.println(repository.updateEmployeeEmail("rahul.ghosh@ymail.com", 2).toString());
		//repository.deleteEmployee(3);
		repository.getEmployees().stream().forEach(System.out::println);
		
		
	}

}
