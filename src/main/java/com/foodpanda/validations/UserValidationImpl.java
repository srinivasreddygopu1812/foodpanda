package com.foodpanda.validations;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.foodpanda.pojos.UserPojo;
import com.foodpanda.services.UserControllerService;
@Component
public class UserValidationImpl {
	final static Logger logger=Logger.getLogger(UserValidationImpl.class);

	public boolean isTextEmpty(String text) {
		logger.info("entered into isTextEmpty()::UserValidationImpl class ");
		if (text == null || text.isEmpty())
			return true;
		logger.info("exited  from isTextEmpty()::UserValidationImpl class ");
		return false;

	}

}
