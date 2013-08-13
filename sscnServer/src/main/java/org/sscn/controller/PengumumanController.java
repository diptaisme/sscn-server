package org.sscn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.services.PengumumanService;

@Controller
public class PengumumanController {
	@Inject
	private PengumumanService pengumumanService;

	@RequestMapping(value = "/cb_instansi.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<RefInstansi>> getInstansis() {
		List<RefInstansi> instansis = pengumumanService.getInstansi(null);
		Map<String, List<RefInstansi>> instansiMap = new HashMap<String, List<RefInstansi>>();
		instansiMap.put("instansis", instansis);
		return instansiMap;
	}
}
