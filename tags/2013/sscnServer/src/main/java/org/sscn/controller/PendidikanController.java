package org.sscn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sscn.persistence.entities.RefPendidikan;
import org.sscn.services.PendidikanService;

@Controller
public class PendidikanController {
	@Inject
	private PendidikanService pendidikanService;

	@RequestMapping(value = "/findPendidikanLikeByName.do", method = RequestMethod.GET)
	@ResponseBody
	public String findPendidikanLikeByName(
			@RequestParam("callback") String callBack,
			@RequestParam("name_startsWith") String name) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<RefPendidikan> pendidikans = pendidikanService
				.findPendidikanByLikeName(name);
		Map<String, Object> mapResult = new HashMap<String, Object>();
		mapResult.put("pendidikans", pendidikans);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				mapResult));
	}

}