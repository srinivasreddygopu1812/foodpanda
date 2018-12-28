package com.foodpanda.pojos;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class Passport {
	
	/*static Passport  ston=null;
	
	public static Passport getInstance() {
		if(ston==null)
		{
			 ston = new Passport ();
			 return ston;
		}
		else {
			return ston;
		}
		
		
	}*/
	public Passport() {
		System.out.println("passport objs created");
	}
	
	private Integer pId;
	private String pIssueLocation;
	
	public String getpIssueLocation() {
		return pIssueLocation;
	}
	public void setpIssueLocation(String pIssueLocation) {
		this.pIssueLocation = pIssueLocation;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	

}
