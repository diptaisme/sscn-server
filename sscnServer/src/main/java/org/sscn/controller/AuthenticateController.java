package org.sscn.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.sscn.persistence.entities.DtUser;
import org.sscn.services.AuthenticateService;

@Controller
public class AuthenticateController {
	@Inject
	private AuthenticateService authenticateService;

	@RequestMapping(value = "/processLogin.do", method = RequestMethod.POST)
	public String login(HttpSession session, @RequestParam("username") String username,
			@RequestParam("password") String password) {

		DtUser user = authenticateService.login(username, password);
		if (user != null) {
			// set session nanti di sini
			session.setAttribute("userLogin", user);
			return "dashboard"; // misal halaman Dashboard.jsp
		} else {
			return "login"; // misal halaman Login.jsp
		}
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	public String logout(HttpSession session) {
		if(session.getAttribute("userLogin")!=null){
			session.removeAttribute("userLogin");
			session.invalidate();
		}
		return "login";
	}
}
