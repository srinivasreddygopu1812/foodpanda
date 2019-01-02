package com.foodpanda.services;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodpanda.controllers.Reader;
import com.foodpanda.controllers.UserController;
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
	
	final static Logger logger=Logger.getLogger(UserControllerService.class);


	public String register(UserPojo up, Model m) throws ClassNotFoundException, SQLException {

		 logger.info("entered into register()::UserControllerService class ");

		///// validation related here//////
		boolean result = uvi.isTextEmpty(up.getEmailId());
		if (result) {
			logger.warn("Invalid mail  ");
			m.addAttribute("msg", "mail is mandatory field");
			m.addAttribute("user", up);
			return "Register";
		}
		result = uvi.isTextEmpty(up.getMobile());
		if (result) {
			logger.warn("Invalid mobile ");

			m.addAttribute("msg", "mobile is mandatory field");
			m.addAttribute("user", up);
			return "Register";
		}
		result = uvi.isTextEmpty(up.getName());
		if (result) {
			logger.warn("Invalid name ");
			m.addAttribute("msg", "name is mandatory field");
			m.addAttribute("user", up);

			return "Register";

		}
		result = uvi.isTextEmpty(up.getPassword());
		if (result) {
			logger.warn("Invalid password ");
			m.addAttribute("msg", "password is mandatory field");
			m.addAttribute("user", up);

			return "Register";

		}

		///// dao related code here///

		try {
logger.warn("in try block:: register()");
			ucd.register(up);
			m.addAttribute("msg", "You have registered successfully");
			 logger.info("exited from register()::UserControllerService class ");
			return "login";

		}
		/// exceptions here////

		catch (ConstraintViolationException e) {
logger.error("Account already registered with user name "+up.getEmailId(), e);
logger.error("Account already registered with mobile number "+up.getMobile(), e);
			m.addAttribute("msg", "Account already exists, use email and phone number should be unique");
			System.out.println("exited from register()::UserControllerService class ");
			return "Register";
		} catch (Exception e) {
			logger.error("Something went wrong with database", e);;
			m.addAttribute("msg", "something went wrong wait for sometime");
			System.out.println("exited from register()::UserControllerService class ");
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


	public String deleteUsers(Principal p,Model m,  String mail, String role) {
		logger.info("entered into deleteUsers()!!UserControllerService class ");
		String loggedinUser = p.getName();
		if(loggedinUser.equals(mail)||role.equals("ADMIN"))
		{
			logger.warn("in if condition");
			m.addAttribute("errorMsg", "You Can't delete");
			String show_all  = Reader.getAll(m);
			logger.info("exited from deleteUsers()!!UserControllerService class ");
			return show_all;
		}
		else {
			logger.warn("in else condition");
			List<UserPojo> list = ucd.deleteUsers(mail);

			m.addAttribute("empList", list);
			logger.info("exited from deleteUsers()!!UserControllerService class ");
			return "show_all";
		}
		
		

	}

	public String editView( String emailid, Model m,Principal p,String role) {
		logger.info("entered into editView()!!UserControllerService class ");
		String loggedinUser = p.getName();
		List<UserPojo> list = ucd.getDetailsByUserName(loggedinUser);
		String loggedinUserRole = list.get(0).getRole();
		
		
		if(loggedinUser.equals(emailid)||loggedinUserRole.equals("ADMIN")&&role.equals("USER"))
		{
			
			
			List list1 = ucd.editView(emailid);

			m.addAttribute("empList", list1.get(0));
			logger.info("exited from editView()!!UserControllerService class ");
			return "updateview";
		}
		else {
			String show_all = Reader.getAll(m);
			m.addAttribute("errorMsg","You don't have access to do this operation ");
			logger.info("exited from editView()!!UserControllerService class ");
			return show_all;
		}
		

	}

	public String updateView(UserPojo up, Model m) {
		logger.info("entered into updateView()!!UserControllerService class ");
		List list;
		try {
			list = ucd.updateView(up);
			m.addAttribute("empList", list);
			logger.info("exited from updateView()!!UserControllerService class ");
			return "show_all";
		}
		catch (ConstraintViolationException e) {
			logger.error("Mobile number already exists,update new mobile number ",e);
			m.addAttribute("errorMsg", "Mobile number already exists,update new mobile number");
			logger.info("exited from updateView()!!UserControllerService class ");
			return "updateview";
		}

		catch (StaleObjectStateException e) {
			logger.error("Requested User details are already updated,so please take latest one ",e);
			m.addAttribute("errorMsg",
					"Requested User details are already updated,so please take latest one by clicking on update button");
			String show_all  = Reader.getAll(m);
			logger.info("exited from updateView()!!UserControllerService class ");
			return show_all;
		}

	}

	public String getDetails(String emailId,Model m) {
		logger.info("entered into getDetails()!!UserControllerService class ");
		List<UserPojo> list = ucd.getDetailsByUserName(emailId);
		String role = list.get(0).getRole();
		logger.debug("logged in user role"+ role);
		if(role.equals("ADMIN"))
		{
			logger.warn("in if condition");
			m.addAttribute("empDetails",list.get(0) );
			logger.info("exited from getDetails()!!UserControllerService class ");
			return "profile_admin";
		}
		else {
			logger.warn("in else condition");
			m.addAttribute("empDetails",list.get(0) );
			logger.info("exited from getDetails()!!UserControllerService class ");
			return "profile";
		}
		
	}

	public String goToUpdate(Model m) {
m.addAttribute("emailId", emailId);
		
		return "update";
	}

}

/*public String updateProfile(UserPojo up, Model m) throws ClassNotFoundException, SQLException {
	logger.info("entered in update profile()!!UserControllerService class");
	
	List<UserPojo> list = ucd.updateProfile(up);
	
	if (list.isEmpty()) {
		return "update";
	} else {
		for (UserPojo userPojo : list) {
			m.addAttribute("emailid", userPojo.getEmailId());
			m.addAttribute("password", userPojo.getPassword());
			m.addAttribute("mobile", userPojo.getMobile());
			m.addAttribute("name", userPojo.getName());
			
		}
		
		logger.info("exited from update profile()!!UserControllerService class");
		return "profile";
		
	}
	
}

public String deleteProfile(UserPojo up, Model m) {
	ucd.deleteProfile(emailId);
	
	m.addAttribute("msg", "You have successfully deleted your profile");
	
	return "Register";
	
}*/