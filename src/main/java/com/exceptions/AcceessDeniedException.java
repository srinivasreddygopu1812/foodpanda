package com.exceptions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class AcceessDeniedException {
	
	@RequestMapping(value="/accessDenied")
	public String name() {
		return "error";
	}

}
