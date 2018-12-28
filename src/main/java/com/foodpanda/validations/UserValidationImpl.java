package com.foodpanda.validations;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.foodpanda.pojos.UserPojo;
@Component
public class UserValidationImpl {

	public boolean isTextEmpty(String text) {
		System.out.println("entered into isTextEmpty()::UserValidationImpl class ");
		if (text == null || text.isEmpty())
			return true;
		System.out.println("exited  from isTextEmpty()::UserValidationImpl class ");
		return false;

	}

}
