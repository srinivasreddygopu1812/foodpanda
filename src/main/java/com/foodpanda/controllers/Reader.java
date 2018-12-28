package com.foodpanda.controllers;

import java.util.List;

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

	static {
		Configuration cfg = new Configuration().configure();
		sf = cfg.buildSessionFactory();
	}
	@RequestMapping(value="/showall")
	public static String getAll(Model m) {
		
		Session sn = sf.openSession();

		Query qr = sn.createQuery("from UserPojo");
		List list = qr.list();
		
		m.addAttribute("empList", list);
		/*m.addAttribute("enableReg", "disabled");
		m.addAttribute("enableUpdate", "enabled");*/
		
		
		
		return "show_all";
		
		
		
	}

}
