package com.foodpanda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.foodpanda.pojos.Employee;
import com.foodpanda.pojos.Passport;
@Service
public class Employee_PassportServiceLayer {

	@Autowired

	Employee emp;/*=Employee.getInstance();*/
	

	@Autowired
	
	Employee emp1;/*=Employee.getInstance();*/

	@Autowired

	Employee emp2;/*=Employee.getInstance();*/

	public void testingSingleton_Singleton() {
		emp.seteId(1);
		emp.seteName("srinu");
		emp.seteSalary(25000.50);
		/*Passport p1 = Passport.getInstance();
		emp.setpPort(p1);*/
		
		emp.getpPort().setpId(123456);
		emp.getpPort().setpIssueLocation("knr");

		emp1.seteId(2);
		emp1.seteName("anil");
		/*Passport p2 = Passport.getInstance();

		emp1.setpPort(p2);
*/		emp1.getpPort().setpId(654321);
		emp1.getpPort().setpIssueLocation("hyd");

		/*Passport p3 = Passport.getInstance();
		emp2.setpPort(p3);*/
		emp2.getpPort().setpId(789);

		System.out.println(emp.geteName() + "-" + emp.getpPort().getpId() + "-" + emp.geteSalary());

		

		System.out.println(emp1.geteName() + "-" + emp1.getpPort().getpId()+"-"+emp1.geteSalary());
		System.out.println(emp2.geteName() + "-" + emp2.geteId() + "-" + emp2.geteSalary() + "-"
				+ emp2.getpPort().getpId() + "-" + emp2.getpPort().getpIssueLocation());
		emp2.seteName("santhu");
	}

	public void testProtoType_Prototype() {

		emp.seteId(1);
		emp.seteName("srinu");
		emp.seteSalary(25000.50);
		emp.getpPort().setpId(123456);
		emp.getpPort().setpIssueLocation("knr");

		emp1.seteId(2);
		emp1.seteName("anil");
		emp1.seteSalary(25555.50);
		emp1.getpPort().setpId(654321);
		emp1.getpPort().setpIssueLocation("hyd");

		emp2.seteName("santhu");
		emp2.getpPort().setpId(789);

		System.out.println(emp.geteName() + "-" + emp.getpPort().getpId() + "-" + emp.geteSalary());

		System.out.println(emp2.geteName() + "-" + emp2.geteId() + "-" + emp2.geteSalary() + "-"
				+ emp2.getpPort().getpId() + "-" + emp2.getpPort().getpIssueLocation());

		System.out.println(emp1.geteName() + "-" + emp1.getpPort().getpId());

	}

	public void testSingleton_protoType() {

		emp.seteId(1);
		emp.seteName("srinu");
		emp.seteSalary(25000.50);
		emp.getpPort().setpId(123456);
		emp.getpPort().setpIssueLocation("knr");

		emp1.seteId(2);
		emp1.seteName("anil");
		emp1.seteSalary(25555.50);
		emp1.getpPort().setpId(654321);
		emp1.getpPort().setpIssueLocation("hyd");

		emp2.seteName("santhu");
		emp2.getpPort().setpId(789);

		System.out.println(emp.geteName() + "-" + emp.getpPort().getpId() + "-" + emp.geteSalary());

		System.out.println(emp2.geteName() + "-" + emp2.geteId() + "-" + emp2.geteSalary() + "-"
				+ emp2.getpPort().getpId() + "-" + emp2.getpPort().getpIssueLocation());

		System.out.println(emp1.geteName() + "-" + emp1.getpPort().getpId());
	}

	public void testProtoType_SingleTon() {

		emp.seteId(1);
		emp.seteName("srinu");
		emp.seteSalary(25000.50);
		emp.getpPort().setpId(123456);
		emp.getpPort().setpIssueLocation("knr");

		emp1.seteId(2);
		emp1.seteName("anil");
		emp1.seteSalary(25555.50);
		emp1.getpPort().setpId(654321);
		emp1.getpPort().setpIssueLocation("hyd");

		emp2.seteName("santhu");
		emp2.getpPort().setpId(789);

		System.out.println(emp.geteName() + "-" + emp.getpPort().getpId() + "-" + emp.geteSalary());

		System.out.println(emp2.geteName() + "-" + emp2.geteId() + "-" + emp2.geteSalary() + "-"
				+ emp2.getpPort().getpId() + "-" + emp2.getpPort().getpIssueLocation());

		System.out.println(emp1.geteName() + "-" + emp1.getpPort().getpId());
		
	}

}
