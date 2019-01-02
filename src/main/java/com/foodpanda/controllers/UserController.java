package com.foodpanda.controllers;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
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
final static Logger logger=Logger.getLogger(UserController.class);
	
@RequestMapping(value = "/register",method=RequestMethod.POST)
	public String register(UserPojo up, Model m) throws ClassNotFoundException, SQLException {
		logger.info("entered into register()!!UserController class with "+up.getEmailId());

		String result = ucs.register(up, m);
		logger.info("exited from register()!! UserController class  ");
		logger.info("registration success with " +up.getEmailId());
		return result;

	}


	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteUsers(Principal p,Model m, @RequestParam("mailId") String mail, @RequestParam("role") String role) {
		logger.info("entered into deleteUsers()!!UserController class ");
		String result = ucs.deleteUsers(p,m, mail, role);
		logger.info("exited from deleteUsers()!!UserController class ");
		 return result;
	}

	@RequestMapping(value = "/editView")
	public String editView(@RequestParam("email") String emailid,@RequestParam("role") String role, Model m,Principal p) {
	logger.info("entered into editView()!!UserController class ");
		 String result = ucs.editView(emailid, m,p,role);
		 logger.info("exited from editView()!!UserController class ");
		 return result;

	}

	@RequestMapping(value = "/updateView", method = RequestMethod.POST)

	public String updateView(UserPojo up, Model m) {
		logger.info("entered into updateView()!!UserController class ");
		String result = ucs.updateView(up, m);

	 logger.info("exited from updateView()!!UserController class ");
	 return result;

	}

	@RequestMapping(value="/goToLogin")
	public String goToLogin(UserPojo up,Model m) {
		logger.info("entered into goToLogin()::UserController class ");
		logger.info("exited  from goToLogin()::UserController class ");
		return "login";
	}

	@RequestMapping(value="/goToLoginFail")
	public String goToLoginFail(Model m) {
		logger.info("entered into goToLoginFail()::UserController class ");
		logger.info("exited  from goToLoginFail()::UserController class ");
		m.addAttribute("msg", "Entered Invalid Credentials,try with correct credentials");
return "login";
	}
	
	@RequestMapping(value="/defaultTarget")
	public String defaultTarget(Principal p,Model m) throws SQLException  {
		logger.info("entered into defaultTarget()::UserController class ");
		logger.debug("logged in username is "+p.getName());
		String emailId = p.getName();
		 String details = ucs.getDetails(emailId,m);
		 logger.info("exited from defaultTarget()::UserController class ");
		return details;
	}

}




/*@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateProfile(UserPojo up, Model m) throws ClassNotFoundException, SQLException {
		logger.info("entered in update profile()!!UserController class");
		 String result = ucs.updateProfile(up, m);
		 logger.info("exited from update profile()!!UserController class");
		 return result;

	}

	@RequestMapping(value = "/delete1")
	public String deleteProfile(UserPojo up, Model m) {
		System.out.println("entered into deleteProfile()!!UserController class ");
		 String result = ucs.deleteProfile(up, m);
		 System.out.println("exited from deleteProfile()!!UserController class ");
		 return result;

	}*/
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
/*@RequestMapping(value="/goToUpdate")
	public String goToUpdate(Model m) {
		String result = ucs.goToUpdate(m);
		return result;
	}
	@RequestMapping(value="/goToDelete")
	public String goToDelete() {
		
		return"delete";
	}*/