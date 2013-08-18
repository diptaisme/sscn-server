package org.sscn.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sscn.dao.DtPersyaratanDao;
import org.sscn.dao.DtUserDao;
import org.sscn.persistence.entities.DtPersyaratan;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.services.PersyaratanService;
import org.sscn.services.UserService;
import org.sscn.util.json.StandardJsonMessage;

@Controller
public class PersyaratanController {
	@Inject
	private PersyaratanService syaratService;
	
	@Inject
	private DtPersyaratanDao syaratDao;
	
	@Inject
	private DtUserDao userDao;

	@RequestMapping(value = "/syarat.do", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			return "redirect:login.do";
		}

		List<DtPersyaratan> syarats = syaratDao.findByProperty("refInstansi", user.getRefInstansi());
		model.addAttribute("reqs", syarats);

		return "persyaratanmanagement";
	}

	@RequestMapping(value = "/syaratSave.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage save(@RequestParam("syarat") String psyarat,HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Save Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		DtPersyaratan syarat = null;
		
		DtUser user = userDao.findById(userLogin.getUsername());
		try {
			syarat = new DtPersyaratan();
			syarat.setSyarat(psyarat);
			syarat.setUser(user);
			
			Map<String, Object> propertiesMap  = new HashMap<String, Object>();
			propertiesMap.put("refInstansi", user.getRefInstansi());			
			syarat.setUrutan(syaratDao.countByMapOfProperties(propertiesMap) + 1);
			syarat.setUser(user);
			syarat.setRefInstansi(user.getRefInstansi());
			
			DtPersyaratan resSyarat = syaratService.simpanPersyaratan(syarat);
			if (resSyarat != null) {
				resSyarat.setRefInstansi(null);
				resSyarat.setUser(null);
				res = new StandardJsonMessage(1, resSyarat, null, "Save Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Save Gagal");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Save Gagal " + e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "/syaratUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage update(
			@RequestParam("id") String id,
			@RequestParam("syarat") String psyarat, HttpSession session)
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
			user = userDao.findById(userLogin.getUsername());
			DtPersyaratan syarat = syaratDao.findUniqueByProperty("id", Integer.parseInt(id), null,null);
			syarat.setSyarat(psyarat);
			syarat.setUser(user);
			
			Map<String, Object> propertiesMap  = new HashMap<String, Object>();
			propertiesMap.put("refInstansi", user.getRefInstansi());			
			syarat.setUser(user);
			syarat.setRefInstansi(user.getRefInstansi());
			DtPersyaratan resSyarat = syaratService.simpanPersyaratan(syarat); 	
			if (resSyarat != null) {
				resSyarat.setRefInstansi(null);
				resSyarat.setUser(null);
				res = new StandardJsonMessage(1, syarat, null, "Update Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Update Gagal");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Update Gagal " + ex.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "/syaratDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage delete(
			@RequestParam("id") String id, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Session null");
			return res;
		}

		StandardJsonMessage res = null;
		//DtUser user = null;
		try {
			DtPersyaratan syarat = syaratDao.findUniqueByProperty("id", Integer.parseInt(id), null,null);
			if (syaratService.delete(syarat)) {
				res = new StandardJsonMessage(1, null, null, "Delete Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Delete Gagal");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Delete Gagal " + ex.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "/getSyarat.do", method = RequestMethod.GET)
	@ResponseBody
	public StandardJsonMessage getPersyaratan(
			@RequestParam("id") String id, HttpSession session) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Get User Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		DtPersyaratan syarat = null;
		try {
			syarat = syaratDao.findUniqueByProperty("id", Integer.parseInt(id), null,null);
			
			syarat.setRefInstansi(null);
			syarat.setUser(null);
			
			res = new StandardJsonMessage(1, syarat, null, "Get User Success");
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Get User Gagal");
		}

		return res;
	}
}
