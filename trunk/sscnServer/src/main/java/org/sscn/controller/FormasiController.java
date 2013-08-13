package org.sscn.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sscn.dao.DtFormasiDao;
import org.sscn.dao.DtUserDao;
import org.sscn.dao.MFormasiDao;
import org.sscn.dao.RefInstansiDao;
import org.sscn.dao.RefJabatanDao;
import org.sscn.dao.RefLokasiDao;
import org.sscn.dao.RefPendidikanDao;
import org.sscn.persistence.entities.DtFormasi;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.MFormasi;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.persistence.entities.RefJabatan;
import org.sscn.persistence.entities.RefLokasi;
import org.sscn.persistence.entities.RefPendidikan;
import org.sscn.util.json.StandardJsonMessage;

@Controller
public class FormasiController {
	@Inject
	private RefInstansiDao refInstansiDao;
	
	@Inject
	private RefLokasiDao refLokasiDao;
	
	@Inject
	private RefJabatanDao refJabatanDao;
	
	@Inject
	private RefPendidikanDao refPendidikanDao;

	@Inject
	private MFormasiDao mFormasiDao;
	
	@Inject
	private DtFormasiDao dtFormasiDao;
	
	@Inject
	private DtUserDao dtUserDao;

	@RequestMapping(value = "/formasi.do", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session) {
		DtUser user = (DtUser) session.getAttribute("userLogin");

		if (user == null) {
			return "redirect:login.do";
		}
		if (!(user.getKewenangan().equalsIgnoreCase("1") || !user
				.getKewenangan().equalsIgnoreCase("2"))) {
			return "redirect:login.do";
		}

		// RefInstansi instansi =
		// refInstansiDao.findById(user.getRefInstansi().getKode());
		model.addAttribute("username", user.getNama());
		DtUser userp = dtUserDao.findById(user.getUsername());
//		RefInstansi instansi = user.getRefInstansi();
//		Hibernate.initialize(user);
		model.addAttribute("instansiNama", userp.getRefInstansi().getNama());

		List<MFormasi> formasis = mFormasiDao.findAll(null);
		model.addAttribute("formasis", formasis);
		return "formasi";
	}

	@RequestMapping(value = "/formasiSave.do", method = RequestMethod.POST)
	public String save(@RequestParam("lokasi") String lokasi,
			@RequestParam("jabatan") String jabatan,
			@RequestParam("pendidikan[]") String[] pendidikan,
			@RequestParam("pendidikanJmlh[]") String[] pendidikanJml,
			@RequestParam("jumlah") String jumlah, HttpSession session,
			ModelMap model) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			//model.addAttribute("userLogin", userLogin);
			//StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
			//		"session empty");
			return "redirect:login.do";
		}

		MFormasi nformasi = new MFormasi();
		RefLokasi refLokasi = refLokasiDao.findById(lokasi); 
		nformasi.setRefLokasi(refLokasi);
		
		RefJabatan refJabatan = refJabatanDao.findById(jabatan);
		nformasi.setRefJabatan(refJabatan);
		
		DtUser user = dtUserDao.findById(userLogin.getUsername());
		nformasi.setRefInstansi(user.getRefInstansi());
		
		nformasi.setJumlah(Integer.parseInt(jumlah));
		mFormasiDao.insert(nformasi);
		
		DtFormasi detFormasi = new DtFormasi(nformasi);
		RefPendidikan objPendidikan = refPendidikanDao.findById(pendidikan[0]);
		detFormasi.setPendidikan(objPendidikan);
		detFormasi.setJumlah(Integer.parseInt(pendidikanJml[0]));
		dtFormasiDao.insert(detFormasi);
		
//		StandardJsonMessage res = new StandardJsonMessage(1, null, null,
//				"Save Success");
		return "redirect:formasi.do";
	}

}
