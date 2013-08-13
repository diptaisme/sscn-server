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
import org.sscn.dao.DtUserDao;
import org.sscn.dao.RefInstansiDao;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.services.UserService;
import org.sscn.util.json.CustomGenericResponse;
import org.sscn.util.json.CustomUserResponse;
import org.sscn.util.json.ObjectConverterJson;
import org.sscn.util.json.StandardJsonMessage;

@Controller
public class UserController {
	@Inject
	private UserService userService;

	@Inject
	private RefInstansiDao refInstansiDao;

	@Inject
	private DtUserDao dtUserDao;

	@RequestMapping(value = "/usersPage.do", method = RequestMethod.GET)
	public String getUsersPage() {
		// This will resolve to /WEB-INF/jsp/Users.jsp page
		return "Users";
	}

	/**
	 * The default method when a request to /users is made. This essentially
	 * retrieves all users, which are wrapped inside a CustomUserResponse
	 * object. The object is automatically converted to JSON when returning back
	 * the response. The @ResponseBody is responsible for this behavior.
	 */
	@RequestMapping(value = "/listUser.do", method = RequestMethod.GET)
	public @ResponseBody
	CustomUserResponse getAll() {

		// Retrieve all users from the service
		List<DtUser> users = userService.getAllUser(new int[] { 0, 20 });

		// Initialize our custom user response wrapper
		CustomUserResponse response = new CustomUserResponse();

		// Assign the result from the service to this response
		response.setRows(ObjectConverterJson.convertFromListDtUser(users));

		// Assign the total number of records found. This is used for paging
		response.setRecords(String.valueOf(users.size()));

		// Since our service is just a simple service for teaching purposes
		// We didn't really do any paging. But normally your DAOs or your
		// persistence layer should support this
		// Assign a dummy page
		response.setPage("1");

		// Same. Assign a dummy total pages
		response.setTotal("20");

		// Return the response
		// Spring will automatically convert our CustomUserResponse as JSON
		// object.
		// This is triggered by the @ResponseBody annotation.
		// It knows this because the JqGrid has set the headers to accept JSON
		// format when it made a request
		// Spring by default uses Jackson to convert the object to JSON
		return response;
	}

	/**
	 * Edit the current user.
	 */
	@RequestMapping(value = "/editUser.do", method = RequestMethod.POST)
	public @ResponseBody
	CustomGenericResponse edit(@RequestParam("nip") String nip,
			@RequestParam("nama") String nama,
			@RequestParam("password") String password,
			@RequestParam("instansi") String instansi,
			@RequestParam("kewenangan") String kewenangan,
			@RequestParam("nipAdmin") String nipAdmin) {

		// Construct our user object
		DtUser user = new DtUser();
		user.setNip(nip);
		user.setNama(nama);
		user.setPassword(password);
		user.setKewenangan(kewenangan);
		user.setTglUpdated(new Date());
		user.setNipAdmin(nipAdmin);

		// Call service to edit
		Boolean success = userService.editUser(user, instansi);

		// Check if successful
		if (success) {
			// Success. Return a custom response
			CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess("true");
			response.setMessage("Action successful!");
			return response;

		} else {
			// A failure. Return a custom response as well
			CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess("false");
			response.setMessage("Action failure!");
			return response;
		}

	}

	/**
	 * Add a new user
	 */
	@RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
	public @ResponseBody
	CustomGenericResponse add(@RequestParam("nip") String nip,
			@RequestParam("nama") String nama,
			@RequestParam("password") String password,
			@RequestParam("instansi") String kodeInstansi,
			@RequestParam("kewenangan") String kewenangan,
			@RequestParam("nipAdmin") String nipAdmin) {

		DtUser user = new DtUser();
		user.setNip(nip);
		user.setNama(nama);
		user.setPassword(password);
		user.setKewenangan(kewenangan);
		user.setTglCreated(new Date());
		user.setTglUpdated(new Date());
		user.setNipAdmin(nipAdmin);

		// System.out.print("kewenangan = "+kewenangan+" instansi"+kodeInstansi);
		kodeInstansi = kodeInstansi.substring(kodeInstansi.length() - 4,
				kodeInstansi.length());

		// Call service to add
		Boolean success = userService.addUser(user, kodeInstansi);

		if (success) {
			// Success. Return a custom response
			CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess("true");
			response.setMessage("Action successful!" + password + nip
					+ password);
			return response;

		} else {
			// A failure. Return a custom response as well
			CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess("false");
			response.setMessage("Action failure!");
			return response;
		}

	}

	/**
	 * Delete an existing user
	 */
	@RequestMapping(value = "/deleteUser.do", method = RequestMethod.POST)
	public @ResponseBody
	CustomGenericResponse delete(@RequestParam("nip") String nip) {

		// Call service to add
		Boolean success = userService.deleteUser(nip);

		// Check if successful
		if (success == true) {
			// Success. Return a custom response
			CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess("true");
			response.setMessage("Action successful!");
			return response;

		} else {
			// A failure. Return a custom response as well
			CustomGenericResponse response = new CustomGenericResponse();
			response.setSuccess("false");
			response.setMessage("Action failure!");
			return response;
		}
	}

	// camplenk here
	@RequestMapping(value = "/user.do", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("userLogin", user);
			return "redirect:login.do";
		}

		List<DtUser> users = dtUserDao.findAll(null);
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
					"Save Success");
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
					"Save Success");
			return res;
		}

		//

		StandardJsonMessage res = null;
		DtUser user = null;
		try {
			user = dtUserDao.findByProperty("username", username, null).get(0);

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
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(1, user, null, "Save Gagal");
		}
		return res;
	}
	
	@RequestMapping(value = "/userDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage delete(
			@RequestParam("username") String username,HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Save Success");
			return res;
		}

		StandardJsonMessage res = null;
		DtUser user = null;
		try {
			user = dtUserDao.findByProperty("username", username, null).get(0);
			
			if (dtUserDao.remove(user)) {
				res = new StandardJsonMessage(1, user, null, "Save Success");
			} else {
				res = new StandardJsonMessage(1, user, null, "Save Gagal");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(1, user, null, "Save Gagal");
		}
		return res;
	}
}
