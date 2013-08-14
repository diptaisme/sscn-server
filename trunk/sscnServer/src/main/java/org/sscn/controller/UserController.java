package org.sscn.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sscn.persistence.entities.DtUser;
import org.sscn.services.UserService;
import org.sscn.util.json.StandardJsonMessage;

@Controller
public class UserController {
	@Inject
	private UserService userService;

	@RequestMapping(value = "/user.do", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("userLogin", user);
			return "redirect:login.do";
		}

		List<DtUser> users = userService.getAllUser(null);
		model.addAttribute("users", users);

		return "usermanagement";
	}

	@RequestMapping(value = "/userSave.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage save(@RequestParam("username") String username,
			@RequestParam("nip") String nip, @RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("instansi") String instansiKd,
			@RequestParam("profile") String profile, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Save Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		DtUser user = null;
		try {
			user = new DtUser();
			user.setNip(nip);
			user.setNama(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setKewenangan(profile);
			user.setTglCreated(new Date());
			user.setTglUpdated(new Date());
			user.setNipAdmin(userLogin.getNip());

			if (userService.addUser(user, instansiKd)) {
				res = new StandardJsonMessage(1, user, null, "Save Success");
			} else {
				res = new StandardJsonMessage(1, user, null, "Save Gagal");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = new StandardJsonMessage(1, user, null, "Save Gagal");
		}
		return res;
	}

	// roberto
	@RequestMapping(value = "/userUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage update(
			@RequestParam("username") String username,
			@RequestParam("nip") String nip, @RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("instansi") String instansiKd,
			@RequestParam("profile") String profile, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Update Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		DtUser user = null;
		try {
			user = userService.findByProperty("username", username, null)
					.get(0);

			user.setNip(nip);
			user.setNama(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setKewenangan(profile);
			user.setTglCreated(new Date());
			user.setTglUpdated(new Date());
			user.setNipAdmin(userLogin.getNip());

			if (userService.addUser(user, instansiKd)) {
				res = new StandardJsonMessage(1, user, null, "Update Success");
			} else {
				res = new StandardJsonMessage(1, user, null, "Update Gagal");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(1, user, null, "Update Gagal");
		}
		return res;
	}

	@RequestMapping(value = "/userDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage delete(
			@RequestParam("username") String username, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Delete Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		DtUser user = null;
		try {
			user = userService.findByProperty("username", username, null)
					.get(0);
			if (userService.deleteUserByUsername(user.getUsername())) {
				res = new StandardJsonMessage(1, user, null, "Delete Success");
			} else {
				res = new StandardJsonMessage(1, user, null, "Delete Gagal");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(1, user, null, "Delete Gagal");
		}
		return res;
	}

	@RequestMapping(value = "/getUser.do", method = RequestMethod.GET)
	public StandardJsonMessage getUser(
			@RequestParam("username") String username, HttpSession session,
			ModelMap model) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Get User Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		DtUser dtUser = null;
		try {
			dtUser = userService.findByProperty("username", username, null)
					.get(0);
			res = new StandardJsonMessage(1, dtUser, null, "Get User Success");
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(1, dtUser, null, "Get User Gagal");
		}

		return res;
	}
}
