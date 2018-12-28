package com.foodpanda.controllers;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodpanda.daos.UserControllerDaoImpl;
import com.foodpanda.daos.UserControllerJdbcDaoImpl;
import com.foodpanda.interfaces.Idaos;
import com.foodpanda.pojos.UserPojo;
import com.foodpanda.services.Employee_PassportServiceLayer;
import com.foodpanda.services.UserControllerService;
import com.foodpanda.validations.UserValidationImpl;

@Controller
public class UserController {

	@Autowired
	UserControllerService ucs;

	@Autowired
	Employee_PassportServiceLayer ep;

	@RequestMapping(value = "/register",method=RequestMethod.POST)
	public String register(UserPojo up, Model m) throws ClassNotFoundException, SQLException {
		System.out.println("entered into register()::UserController class ");

		String result = ucs.register(up, m);
		System.out.println("exited from register()::UserController class ");
		return result;

	}
	/*@RequestMapping(value="/defaultTarget")
	public String loginCheck(UserPojo up, Model m) throws SQLException {
		System.out.println("entered into loginCheck()::UserController class ");
		String result = ucs.loginCheck(up, m);
		
		  ep.testingSingleton_Singleton(); 
		  ep.testProtoType_Prototype();
		  ep.testSingleton_protoType(); ep.testProtoType_SingleTon();
		 
		System.out.println("exited from loginCheck()::UserController class ");

		return result;

	}*/

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateProfile(UserPojo up, Model m) throws ClassNotFoundException, SQLException {
		System.out.println("entered in update profile()!!UserController class");
		return ucs.updateProfile(up, m);

	}

	@RequestMapping(value = "/delete1")
	public String deleteProfile(UserPojo up, Model m) {
		System.out.println("entered into deleteProfile()!!UserController class ");
		System.out.println("exited from deleteProfile()!!UserController class ");
		return ucs.deleteProfile(up, m);

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteUsers(Principal p,Model m, @RequestParam("mailId") String mail, @RequestParam("role") String role) {
		System.out.println("entered into deleteUsers()!!UserController class ");
		System.out.println("exited from deleteUsers()!!UserController class ");
		return ucs.deleteUsers(p,m, mail, role);

	}

	@RequestMapping(value = "/editView")
	public String editView(@RequestParam("email") String emailid,@RequestParam("role") String role, Model m,Principal p) {
		System.out.println("entered into editView()!!UserController class ");
		System.out.println("exited from editView()!!UserController class ");
		return ucs.editView(emailid, m,p,role);

	}

	@RequestMapping(value = "/updateView", method = RequestMethod.POST)

	public String updateView(UserPojo up, Model m) {
		System.out.println("entered into updateView()!!UserController class ");
		System.out.println("exited from updateView()!!UserController class ");
System.out.println("Before updating the  version is"+up.getVersion());
		return ucs.updateView(up, m);

	}

	@RequestMapping(value="/goToLogin")
	public String goToLogin(UserPojo up,Model m) {
		System.out.println("through goToLogin()!!UserContr ");
		return "login";
	}
	@RequestMapping(value="/goToLoginFail")
	public String goToLoginFail(Model m) {
		m.addAttribute("msg", "Entered Invalid Credentials,try with correct credentials");
return "login";
	}
	
	@RequestMapping(value="/defaultTarget")
	public String defaultTarget(Principal p,Model m) throws SQLException  {
		System.out.println(p.getName());
		String emailId = p.getName();
		 String details = ucs.getDetails(emailId,m);
		
		return details;
	}

}



