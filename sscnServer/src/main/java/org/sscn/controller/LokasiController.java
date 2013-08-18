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
import org.sscn.persistence.entities.RefLokasi;
import org.sscn.services.LokasiService;
import org.sscn.util.json.StandardJsonMessage;

@Controller
public class LokasiController {

	@Inject
	private LokasiService lokasiService;
	
	@RequestMapping(value = "/lokasi.do", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}

		List<RefLokasi> lokasis = lokasiService.findAllLokasiByInstansi(user.getRefInstansi().getKode(), null);
		model.addAttribute("lokasis", lokasis);

		return "lokasimanagement";
	}

	@RequestMapping(value = "/findLokasiLikeByName.do", method = RequestMethod.GET)
	@ResponseBody
	public String findInstansiLikeByName(
			@RequestParam("callback") String callBack,
			@RequestParam("name_startsWith") String name) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		List<RefLokasi> lokasis = lokasiService.findLokasiByLikeName(name);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lokasis", lokasis);

		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				resultMap));
	}
	
	@RequestMapping(value = "/lokasiSave.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage save(@RequestParam("kode") String kode,
			@RequestParam("nama") String name,
			@RequestParam("instansi") String instansiKd, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Save Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		RefLokasi lokasi = null;
		try {
			lokasi = lokasiService.save(kode, name, instansiKd);
			if (lokasi != null) {
				res = new StandardJsonMessage(1, lokasi, null, "Save Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Save Gagal");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Save Gagal");
		}
		return res;
	}

	@RequestMapping(value = "/lokasiUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage update(@RequestParam("kode") String kode,
			@RequestParam("nama") String name, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Update Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		RefLokasi lokasi = null;
		try {
			lokasi = lokasiService.update(kode, name);
			if (lokasi != null) {			
				RefInstansi pInstansi = new RefInstansi();
				pInstansi.setKode(lokasi.getRefInstansi().getKode());
				pInstansi.setNama(lokasi.getRefInstansi().getNama());
				lokasi.setRefInstansi(pInstansi);
				res = new StandardJsonMessage(1, lokasi, null, "Update Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Update Gagal");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Update Gagal");
		}
		return res;
	}

	@RequestMapping(value = "/lokasiDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage delete(@RequestParam("kode") String kode,
			HttpSession session) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Delete Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		RefLokasi lokasi = null;
		try {
			lokasi = lokasiService.delete(kode);
			if (lokasi != null) {
				RefInstansi temp = new RefInstansi();
				temp.setKode(lokasi.getRefInstansi().getKode());
				temp.setNama(lokasi.getRefInstansi().getNama());
				lokasi.setRefInstansi(temp);
				res = new StandardJsonMessage(1, lokasi, null, "Delete Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Delete Gagal");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Delete Gagal");
		}
		return res;
	}

	@RequestMapping(value = "/getLokasi.do", method = RequestMethod.GET)
	@ResponseBody
	public StandardJsonMessage getLokasi(@RequestParam("kode") String kode,
			HttpSession session) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Get Lokasi Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		RefLokasi lokasi = null;
		try {
			lokasi = lokasiService.findLokasiById(kode);
			RefInstansi pInstansi = new RefInstansi();
			pInstansi.setKode(lokasi.getRefInstansi().getKode());
			pInstansi.setNama(lokasi.getRefInstansi().getNama());
			lokasi.setRefInstansi(pInstansi);
			res = new StandardJsonMessage(1, lokasi, null, "Get Lokasi Success");
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Get Lokasi Gagal");
		}

		return res;
	}

}
