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
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.services.InstansiService;

@Controller
public class InstansiController {

	@Inject
	private InstansiService instansiService;

	@RequestMapping(value = "/instansi.do", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("userLogin", user);
			return "redirect:login.do";
		}

		List<RefInstansi> instansis = instansiService.findAllInstansi(null);
		model.addAttribute("instansis", instansis);
		return "instansimanagement";
	}

	@RequestMapping(value = "/findInstansiLikeByName.do", method = RequestMethod.GET)
	@ResponseBody
	public String findInstansiLikeByName(
			@RequestParam("callback") String callBack,
			@RequestParam("name_startsWith") String name) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<RefInstansi> listInstansis = instansiService
				.findInstansiByLikeName(name);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("instansis", listInstansis);
		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				resultMap));
	}

}
