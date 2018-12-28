package com.foodpanda.pojos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.Scope;
import org.springframework.stereotype.Component;

@Component
@org.springframework.context.annotation.Scope(value="prototype")
public class Employee {
	/*static Employee ston=null;


	public static Employee getInstance() {
		if(ston==null)
		{
			ston= new Employee ();
			return ston;
		}
		else {
			return ston;
		}
		
		
	}*/
	public Employee(Passport pass) {
		this.pPort=pass;
		
		System.out.println("from no argument constructor with passport");
	}
	
	public Employee() {
	
		System.out.println("from no argument constructor");
	}
	public Employee(String s) {
		System.out.println("hello "+s);
		System.out.println("EmployeeSingleTon object created");
	}
	public Employee(String s,Integer i) {
		System.out.println("hello "+s);
		System.out.println("value is "+i);
	}
	
	
	private Integer eId;

	private String eName;
	private Double eSalary;
	@Autowired
	private Passport pPort;
	
	public Passport getpPort() {
		return pPort;
	}
	public void setpPort(Passport pPort) {
		this.pPort = pPort;
	}
	public Integer geteId() {
		return eId;
	}
	public void seteId(Integer eId) {
		this.eId = eId;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public Double geteSalary() {
		return eSalary;
	}
	public void seteSalary(Double eSalary) {
		this.eSalary = eSalary;
	}
	

}
