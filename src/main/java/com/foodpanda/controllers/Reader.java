package com.foodpanda.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Reader {
	static SessionFactory sf;
	final static Logger logger=Logger.getLogger(Reader.class);

	static {
		Configuration cfg = new Configuration().configure();
		sf = cfg.buildSessionFactory();
		logger.warn("session factory builded");
	}
	@RequestMapping(value="/showall")
	public static String getAll(Model m) {
		logger.info("entered into getAll()::Reader class");
		Session sn = sf.openSession();
		logger.warn("session opened");
		Query qr = sn.createQuery("from UserPojo");
		List list = qr.list();
		logger.warn("data retieved");
		m.addAttribute("empList", list);
		logger.warn("list has been set to model attribute");
		
		
		logger.info("exited from getAll()::Reader class");
		return "show_all";
		
		
		
	}

}

/*m.addAttribute("enableReg", "disabled");
		m.addAttribute("enableUpdate", "enabled");*/