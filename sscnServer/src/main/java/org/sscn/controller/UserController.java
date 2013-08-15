package org.sscn.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.services.UserService;
import org.sscn.util.json.StandardJsonMessage;

@Controller
public class UserController {
	@Inject
	private UserService userService;

	@RequestMapping(value = "/user.do", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model, HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			request.setAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
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
				res = new StandardJsonMessage(0, null, null, "Save Gagal");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Save Gagal "
					+ e.getMessage());
		}
		return res;
	}

	// roberto
	@RequestMapping(value = "/userUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage update(
			@RequestParam("username") String username,
			@RequestParam("nip") String nip, @RequestParam("name") String name,
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
			// user.setPassword(password);
			user.setKewenangan(profile);
			user.setTglCreated(new Date());
			user.setTglUpdated(new Date());
			user.setNipAdmin(userLogin.getNip());

			if (userService.editUser(user, instansiKd)) {
				RefInstansi temp = new RefInstansi();
				temp.setKode(user.getRefInstansi().getKode());
				temp.setNama(user.getRefInstansi().getNama());
				user.setRefInstansi(temp);
				res = new StandardJsonMessage(1, user, null, "Update Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Update Gagal");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Update Gagal "
					+ ex.getMessage());
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
			List<DtUser> listUser = userService.findByProperty("username",
					username, null);
			if (listUser.size() == 0) {
				res = new StandardJsonMessage(0, null, null, "Delete Gagal");
			} else {
				user = listUser.get(0);
				
				if (userService.deleteUserByUsername(user.getUsername())) {
					RefInstansi temp = new RefInstansi();
					temp.setKode(user.getRefInstansi().getKode());
					temp.setNama(user.getRefInstansi().getNama());
					user.setRefInstansi(temp);
					res = new StandardJsonMessage(1, user, null,
							"Delete Success");
				} else {
					res = new StandardJsonMessage(0, null, null, "Delete Gagal");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Delete Gagal");
		}
		return res;
	}

	@RequestMapping(value = "/getUser.do", method = RequestMethod.GET)
	@ResponseBody
	public StandardJsonMessage getUser(
			@RequestParam("username") String username, HttpSession session)
			throws Exception {

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

			RefInstansi pInstansi = new RefInstansi();
			pInstansi.setKode(dtUser.getRefInstansi().getKode());
			pInstansi.setNama(dtUser.getRefInstansi().getNama());
			dtUser.setRefInstansi(pInstansi);

			res = new StandardJsonMessage(1, dtUser, null, "Get User Success");
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Get User Gagal");
		}

		return res;
	}
	
	@RequestMapping(value = "/userChangePassword.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage changePassword(
			@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Update Password Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		DtUser user = null;
		try {
			user = userService.findByProperty("username", username, null)
					.get(0);						
			user.setTglUpdated(new Date());
			

			if (userService.changePassword(user, password)) {
				RefInstansi temp = new RefInstansi();
				temp.setKode(user.getRefInstansi().getKode());
				temp.setNama(user.getRefInstansi().getNama());
				user.setRefInstansi(temp);
				res = new StandardJsonMessage(1, user, null, "Update Password Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Update Password Gagal");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Update Password Gagal"
					+ ex.getMessage());
		}
		return res;
	}

}
