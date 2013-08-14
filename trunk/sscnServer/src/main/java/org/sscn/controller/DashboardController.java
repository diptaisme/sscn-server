package org.sscn.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sscn.persistence.entities.DtUser;


@Controller
public class DashboardController {
	
	@RequestMapping(value="/dashboard.do", method = RequestMethod.GET)	
	public String index(ModelMap model, HttpSession session){
		DtUser user = (DtUser)session.getAttribute("userLogin");
		if (user == null){
			return "redirect:login.do";
		} 
		
		
		model.addAttribute("username", user.getNama());
		return "dashboard";
	}

}
