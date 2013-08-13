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
import org.sscn.dao.RefLokasiDao;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.persistence.entities.RefLokasi;

@Controller
public class LokasiController {


	@Inject
	private RefLokasiDao refLokasiDao;

	@RequestMapping(value = "/findLokasiLikeByName.do", method = RequestMethod.GET)
	@ResponseBody
	public String findInstansiLikeByName(
			@RequestParam("callback") String callBack,
			@RequestParam("name_startsWith") String name) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nama", name);
		List<RefLokasi> lokasis = refLokasiDao.findLikeMapOfProperties(
				map, null);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("lokasis", lokasis);

		return objectMapper.writeValueAsString(new JSONPObject(callBack, map2));
	}

}
