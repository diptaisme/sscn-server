package org.sscn.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.sscn.dao.PeriodeDaftarDao;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.PeriodeDaftar;
import org.sscn.services.AuthenticateService;

@Controller
public class AuthenticateController {
	@Inject
	private AuthenticateService authenticateService;
	
	@Inject
	private PeriodeDaftarDao periodeDaftarDao;

	@RequestMapping(value = "/processLogin.do", method = RequestMethod.POST)
	public String login(ModelMap map, HttpSession session,
			@RequestParam("username") String username,
			@RequestParam("password") String password) {

		try {
			DtUser user = authenticateService.login(username, password);
			if (user != null) {
				if (session.getAttribute("userLogin") != null) {
					session.removeAttribute("userLogin");					
				}
				session.setAttribute("userLogin", user);
				List<PeriodeDaftar> periodeDaftars = periodeDaftarDao.findByProperty("kode", user.getRefInstansi().getKode(), null);
				if(periodeDaftars!=null && periodeDaftars.size()>0){
					session.setAttribute("periodeDaftar", periodeDaftars.get(0));
				}
				return "dashboard";
			} else {
				map.addAttribute("pesan",
						"Login anda gagal, silahkan ulangi kembali");
				return "login";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.addAttribute("pesan",
					"Login anda gagal, silahkan ulangi kembali");
			return "login";
		}
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	public String logout(ModelMap map, HttpSession session) {
		if (session.getAttribute("userLogin") != null) {
			session.removeAttribute("userLogin");
			// session.invalidate();
		}
		map.addAttribute("pesan", "Anda telah logout");
		return "login";
	}
}
