package org.sscn.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sscn.dao.PeriodeDaftarDao;
import org.sscn.dao.RefInstansiDao;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.PeriodeDaftar;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.services.InstansiService;

@Controller
public class InstansiController {

	@Inject
	private InstansiService instansiService;
	
	@Inject
	private PeriodeDaftarDao periodeDaftarDao;
	
	@Inject
	private RefInstansiDao refInstansiDao;
	
	/*@RequestMapping(value = "/instansi.do", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}

		List<RefInstansi> instansis = instansiService.findAllInstansi(null);
		model.addAttribute("instansis", instansis);
		return "instansimanagement";
	}*/

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

	@RequestMapping(value = "/periodeDaftar.do", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		PeriodeDaftar periode = this.periodeDaftarDao.findById(user.getRefInstansi().getKode());
		String start = "";
		String startTime = "";
		String end = "";
		String endTime = "";
		String isReadOnly = "";
		if (periode.getStart() != null){
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			start = df.format(periode.getStart());
			DateFormat df1 = new SimpleDateFormat("HH:mm");
			startTime = df1.format(periode.getStart());
			isReadOnly = "readonly='readonly'";
		}
		
		if (periode.getEnd() != null){
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			end = df.format(periode.getEnd());
			DateFormat df1 = new SimpleDateFormat("HH:mm");
			endTime = df1.format(periode.getEnd());
		}
		
		if (!isReadOnly.isEmpty()){
			model.addAttribute("isDisabled", "disabled='disabled'");
		}
		model.addAttribute("isReadOnly", isReadOnly);
		model.addAttribute("startDate", start);
		model.addAttribute("endDate", end);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		return "periodedaftar";
	}
	
	@RequestMapping(value = "/updatePeriodeDaftar.do", method = RequestMethod.POST)
	public String save(ModelMap model, HttpSession session, HttpServletRequest request) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		
		String pstartDate = request.getParameter("startDate") + " " + request.getParameter("startTime");
		String pEndDate = request.getParameter("endDate") + " " + request.getParameter("endTime");
		Date startDate;
		Date endDate;
		try {
			startDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(pstartDate);
			endDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(pEndDate);
			if (startDate.after(endDate)){
				model.addAttribute("pesan", "Tanggal mulai dan selesai tidak sesuai");
				return "redirect:periodeDaftar.do";
			}
		} catch (ParseException e) {
			
			startDate = null;
			endDate = null;
			e.printStackTrace();
		}
		
		PeriodeDaftar periode = this.periodeDaftarDao.findById(user.getRefInstansi().getKode());
		periode.setUsername(user.getUsername());
		periode.setStart(startDate);
		periode.setEnd(endDate);
		
		try {
			PeriodeDaftar res = instansiService.updatePeriodePeserta(periode);
			if (res != null){
				model.addAttribute("pesan", "Data periode daftar berhasil disimpan");
			} else {
				model.addAttribute("pesan", "Data periode daftar gagal disimpan");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("pesan", "Data periode daftar gagal disimpan");
		}
		
		return "redirect:periodeDaftar.do";
		
	}
	
	@RequestMapping(value = "/instansiconf.do", method = RequestMethod.GET)
	public String instansiconf(ModelMap model, HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		RefInstansi instansi = this.refInstansiDao.findById(user.getRefInstansi().getKode());
		
		if (instansi.getPilihanJabatan() != null && instansi.getPilihanLokasiTest() != null){
			model.addAttribute("isDisabled", "disabled='disabled'");
		} else {
			model.addAttribute("isDisabled", "");
		}
		
		model.addAttribute("instansi", instansi);
		
		return "instansimanagement";
	}
	
	@RequestMapping(value = "/saveInstansiconf.do", method = RequestMethod.POST)
	public String saveInstansiConf(ModelMap model, HttpSession session, HttpServletRequest request) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		
		String pilihanJabatan = request.getParameter("maxPilihan");
		String lokasitest = request.getParameter("lokasitest");
		RefInstansi ins = this.refInstansiDao.findById(user.getRefInstansi().getKode());
		ins.setPilihanJabatan(pilihanJabatan);
		ins.setPilihanLokasiTest(lokasitest);
		
		try {
			RefInstansi res = instansiService.updateRefInstansi(ins);
			if (res != null){
				model.addAttribute("pesan", "Data periode daftar berhasil disimpan");
			} else {
				model.addAttribute("pesan", "Data periode daftar gagal disimpan");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("pesan", "Data periode daftar gagal disimpan");
		}
		
		return "redirect:instansiconf.do";
		
	}
}
