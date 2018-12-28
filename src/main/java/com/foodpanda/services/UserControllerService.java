package com.foodpanda.services;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodpanda.controllers.Reader;
import com.foodpanda.interfaces.Idaos;
import com.foodpanda.pojos.UserPojo;
import com.foodpanda.validations.UserValidationImpl;
@Service
public class UserControllerService {

	static String emailId;
	static String password;
	static String mobile;
	static String name;
	@Autowired
	UserValidationImpl uvi;

	@Autowired
	Idaos ucd;

	public String register(UserPojo up, Model m) throws ClassNotFoundException, SQLException {

		System.out.println("entered into register()::UserControllerService class ");

		///// validation related here//////
		boolean result = uvi.isTextEmpty(up.getEmailId());
		if (result) {

			m.addAttribute("msg", "mail is mandatory field");
			m.addAttribute("user", up);
			return "Register";
		}
		result = uvi.isTextEmpty(up.getMobile());
		if (result) {

			m.addAttribute("msg", "mobile is mandatory field");
			m.addAttribute("user", up);
			return "Register";
		}
		result = uvi.isTextEmpty(up.getName());
		if (result) {
			m.addAttribute("msg", "name is mandatory field");
			m.addAttribute("user", up);
			return "Register";

		}
		result = uvi.isTextEmpty(up.getPassword());
		if (result) {
			m.addAttribute("msg", "password is mandatory field");
			m.addAttribute("user", up);
			return "Register";

		}

		///// dao related code here///

		try {

			ucd.register(up);
			m.addAttribute("msg", "You have registered successfully");
			return "login";

		}
		/// exceptions here////

		catch (ConstraintViolationException e) {

			m.addAttribute("msg", "Account already exists, use email and phone number should be unique");
			System.out.println("exited from register()::UserControllerService class ");
			return "Register";
		} catch (Exception e) {
			m.addAttribute("msg", "something went wrong wait for sometime");
			return "Register";
		}

	}

	public String loginCheck(UserPojo up, Model m) throws SQLException {

		System.out.println("entered into loginCheck()::UserController class ");
		boolean result = uvi.isTextEmpty(up.getEmailId());

		if (result) {
			m.addAttribute("msg", "emailid is mandatory field");
			m.addAttribute("user", up);
			return "login";

		}

		result = uvi.isTextEmpty(up.getPassword());
		if (result) {
			m.addAttribute("msg", "password is mandatory field");
			m.addAttribute("user", up);
			return "login";

		}
		List<UserPojo> list = ucd.loginCheck(up);

		if (list.isEmpty()) {
			m.addAttribute("msg", "Entered Invalid Credentials,try with correct credentials");
			return "login";
		} else {

			for (UserPojo upj : list) {
				emailId = upj.getEmailId();
				password = upj.getPassword();
				mobile = upj.getMobile();
				name = upj.getName();

				m.addAttribute("emailid", upj.getEmailId());
				m.addAttribute("password", upj.getPassword());
				m.addAttribute("mobile", upj.getMobile());
				m.addAttribute("name", upj.getName());
			}

			System.out.println("exited from loginCheck()::UserController class ");

			return "profile";

		}
	}

	public String updateProfile(UserPojo up, Model m) throws ClassNotFoundException, SQLException {
		System.out.println("entered in update profile()!!UserControllerService class");

		List<UserPojo> list = ucd.updateProfile(up, emailId, mobile, password);

		if (list.isEmpty()) {
			return "update";
		} else {
			for (UserPojo userPojo : list) {
				m.addAttribute("emailid", userPojo.getEmailId());
				m.addAttribute("password", userPojo.getPassword());
				m.addAttribute("mobile", userPojo.getMobile());
				m.addAttribute("name", userPojo.getName());

			}

			System.out.println("exeted in update profile()!!UserControllerService class");
			return "profile";

		}

	}

	public String deleteProfile(UserPojo up, Model m) {
		ucd.deleteProfile(emailId);

		m.addAttribute("msg", "You have successfully deleted your profile");

		return "Register";

	}

	public String deleteUsers(Principal p,Model m,  String mail, String role) {
		String loggedinUser = p.getName();
		if(loggedinUser.equals(mail)||role.equals("ADMIN"))
		{
			m.addAttribute("errorMsg", "You Can't delete");
			String show_all  = Reader.getAll(m);
			
			return show_all;
		}
		else {
			List<UserPojo> list = ucd.deleteUsers(mail);

			m.addAttribute("empList", list);
			return "show_all";
		}
		
		

	}

	public String editView( String emailid, Model m,Principal p,String role) {
		String loggedinUser = p.getName();
		List<UserPojo> list = ucd.getDetailsByUserName(loggedinUser);
		String loggedinUserRole = list.get(0).getRole();
		
		
		if(loggedinUser.equals(emailid)||loggedinUserRole.equals("ADMIN")&&role.equals("USER"))
		{
			
			
			List list1 = ucd.editView(emailid);

			m.addAttribute("empList", list1.get(0));
			
			return "updateview";
		}
		else {
			String show_all = Reader.getAll(m);
			m.addAttribute("errorMsg","You don't have access to do this operation ");
		return show_all;
		}
		

	}

	public String updateView(UserPojo up, Model m) {
		List list;
		try {
			list = ucd.updateView(up);
			m.addAttribute("empList", list);
			return "show_all";
		}
		catch (ConstraintViolationException e) {
			m.addAttribute("errorMsg", "Mobile number already exists,update new mobile number");
			System.out.println("exited from register()::UserControllerService class ");
			return "updateview";
		}

		catch (StaleObjectStateException e) {
			m.addAttribute("errorMsg",
					"Requested User details are already updated,so please take latest one by clicking on update button");
			String show_all  = Reader.getAll(m);
			return show_all;
		}

	}

	public String getDetails(String emailId,Model m) {
		List<UserPojo> list = ucd.getDetailsByUserName(emailId);
		String role = list.get(0).getRole();
		if(role.equals("ADMIN"))
		{
			m.addAttribute("empDetails",list.get(0) );
			return "profile_admin";
		}
		else {
			m.addAttribute("empDetails",list.get(0) );
			return "profile";
		}
		
	}

}
