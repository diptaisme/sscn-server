package org.sscn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
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

@Controller
public class InstansiController {

	@Inject
	private DtUserDao dtUserDao;

	@Inject
	private RefInstansiDao refInstansiDao;

	@RequestMapping(value = "/instansi.do", method = RequestMethod.GET)
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

	@RequestMapping(value = "/findInstansiLikeByName.do", method = RequestMethod.GET)
	@ResponseBody
	public String findInstansiLikeByName(
			@RequestParam("callback") String callBack,
			@RequestParam("name_startsWith") String name) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nama", name);
		List<RefInstansi> instansis = refInstansiDao.findLikeMapOfProperties(
				map, null);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("instansis", instansis);

		return objectMapper.writeValueAsString(new JSONPObject(callBack, map2));
	}

}
