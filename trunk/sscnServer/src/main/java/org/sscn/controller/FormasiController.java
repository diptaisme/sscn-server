package org.sscn.controller;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import org.sscn.persistence.entities.RefJabatan;
import org.sscn.persistence.entities.RefLokasi;
import org.sscn.persistence.entities.RefPendidikan;

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
		// RefInstansi instansi = user.getRefInstansi();
		// Hibernate.initialize(user);
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
			// model.addAttribute("userLogin", userLogin);
			// StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
			// "session empty");
			return "redirect:login.do";
		}

		try {
			MFormasi nformasi = new MFormasi();
			RefLokasi refLokasi = refLokasiDao.findById(lokasi);
			nformasi.setRefLokasi(refLokasi);

			RefJabatan refJabatan = refJabatanDao.findById(jabatan);
			nformasi.setRefJabatan(refJabatan);

			DtUser user = dtUserDao.findById(userLogin.getUsername());
			nformasi.setRefInstansi(user.getRefInstansi());

			nformasi.setJumlah(Integer.parseInt(jumlah));
			mFormasiDao.insert(nformasi);

			for (int i = 0; i < pendidikan.length; i++) {
				DtFormasi detFormasi = new DtFormasi(nformasi);
				RefPendidikan objPendidikan = refPendidikanDao
						.findById(pendidikan[i]);
				detFormasi.setPendidikan(objPendidikan);
				detFormasi.setJumlah(Integer.parseInt(pendidikanJml[i]));
				dtFormasiDao.insert(detFormasi);
			}

			model.addAttribute("pesan", "Insert berhasil");
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("pesan", "Insert gagal");
		}

		return "redirect:formasi.do";
	}

	// roberto
	@RequestMapping(value = "/formasiUpdate.do", method = RequestMethod.POST)
	public String update(@RequestParam("id") String id,
			@RequestParam("lokasi") String lokasi,
			@RequestParam("jabatan") String jabatan,
			@RequestParam("pendidikan[]") String[] pendidikan,
			@RequestParam("pendidikanJmlh[]") String[] pendidikanJml,
			@RequestParam("jumlah") String jumlah, HttpSession session,
			ModelMap model) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			// model.addAttribute("userLogin", userLogin);
			// StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
			// "session empty");
			return "redirect:login.do";
		}

		try {
			MFormasi nformasi = mFormasiDao.findById(id);
			RefLokasi refLokasi = refLokasiDao.findById(lokasi);
			nformasi.setRefLokasi(refLokasi);

			RefJabatan refJabatan = refJabatanDao.findById(jabatan);
			nformasi.setRefJabatan(refJabatan);

			DtUser user = dtUserDao.findById(userLogin.getUsername());
			nformasi.setRefInstansi(user.getRefInstansi());

			nformasi.setJumlah(Integer.parseInt(jumlah));
			mFormasiDao.update(nformasi);

			Iterator<DtFormasi> iterator = nformasi.getDtFormasis().iterator();
			int i = 0;
			while (iterator.hasNext()) {
				DtFormasi detFormasi = (DtFormasi) iterator.next();
				RefPendidikan objPendidikan = refPendidikanDao
						.findById(pendidikan[i]);
				detFormasi.setPendidikan(objPendidikan);
				detFormasi.setJumlah(Integer.parseInt(pendidikanJml[i]));
				dtFormasiDao.update(detFormasi);
				i++;
			}
			model.addAttribute("pesan", "update berhasil");
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("pesan", "update gagal");
		}
		return "redirect:formasi.do";
	}

	@RequestMapping(value = "/formasiDelete.do", method = RequestMethod.POST)
	public String delete(@RequestParam("id") String id, HttpSession session,
			ModelMap model) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			return "redirect:login.do";
		}

		try {
			MFormasi instance = mFormasiDao.findById(id);
			mFormasiDao.remove(instance);
			model.addAttribute("pesan", "delete berhasil");
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("pesan", "delete gagal");
		}

		return "redirect:formasi.do";
	}
	
	@RequestMapping(value = "/getFormasi.do", method = RequestMethod.GET)
	public String getFormasi(@RequestParam("id") String id, HttpSession session,
			ModelMap model) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			return "redirect:login.do";
		}

		try {
			MFormasi nformasi = mFormasiDao.findById(id);
			model.addAttribute("formasi", nformasi);
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("pesan", "Get Formasi gagal");
		}

		return "formasi.do";
	}

}
