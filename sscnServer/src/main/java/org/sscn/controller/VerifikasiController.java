package org.sscn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.DtPersyaratan;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.DtVerifikasiNok;
import org.sscn.services.PersyaratanService;
import org.sscn.services.UserService;
import org.sscn.services.VerfikasiService;
import org.sscn.util.json.StandardJsonMessage;

@Controller
public class VerifikasiController {
	@Inject
	private UserService userService;

	@Inject
	private DtPendaftaranDao dtPendaftaranDao;

	@Inject
	private VerfikasiService verifikasiServices;

	@Inject
	private PersyaratanService persyaratanServices;

	@RequestMapping(value = "/verifikasi.do", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model, HttpSession session) {
		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			request.setAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}

		DtUser user = userService.findByProperty("username", userLogin.getUsername(), null).get(0);
		List<DtPendaftaran> pendaftars = dtPendaftaranDao.findByInstansi(user.getRefInstansi(),
				null);
		model.addAttribute("pendaftars", pendaftars);

		List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
				user.getRefInstansi(), null, null);
		model.addAttribute("persyaratans", persyaratans);
		return "verifikasi";
	}

	@RequestMapping(value = "/verifikasiSave.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage save(@RequestParam("pendaftarId") String pendaftarId,
			@RequestParam("persyaratanIds[]") String[] persyaratanIds, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null, "Save Gagal");
			return res;
		}

		try {
			DtUser user = userService.findByProperty("username", userLogin.getUsername(), null)
					.get(0);
			List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
					user.getRefInstansi(), null);

			Iterator<DtPersyaratan> iterator = persyaratans.iterator();
			List<DtPersyaratan> gagalPersyaratan = new ArrayList<DtPersyaratan>();
			int found = 0;
			while (iterator.hasNext()) {
				DtPersyaratan dtPersyaratan = iterator.next();
				found = 0;
				for (int i = 0; i < persyaratanIds.length; i++) {
					System.out.println("PersyaratanIds : " + persyaratanIds[i] + " vs " + dtPersyaratan.getId());					
					if (Integer.parseInt(persyaratanIds[i]) == dtPersyaratan.getId()) {
						found++;
					}
				}
				if (found == 0) {
					gagalPersyaratan.add(dtPersyaratan);
				}
			}

			DtPendaftaran pendaftar = dtPendaftaranDao.findUniqueByProperty("id",
					Long.parseLong(pendaftarId), null, null);

			List<DtVerifikasiNok> verNoks = new ArrayList<DtVerifikasiNok>();

			if (gagalPersyaratan.size() > 0) {
				for (int i = 0; i < gagalPersyaratan.size(); i++) {
					DtVerifikasiNok pverNok = new DtVerifikasiNok();
					pverNok.setPendaftar(pendaftar);
					pverNok.setPersyaratan(gagalPersyaratan.get(i));
					pverNok.setVerifikator(user);
					pverNok.setCreatedDate(new Date());
					verNoks.add(pverNok);
				}
			}
			pendaftar.setUserValidate(user.getUsername());

			try {
				Boolean resVer = verifikasiServices.simpanHasilVerifikasi(pendaftar, verNoks);

				if (resVer) {
					return (new StandardJsonMessage(1, null, null, "Save Success"));
				} else {
					return (new StandardJsonMessage(0, null, null, "Save Gagal"));
				}

			} catch (Exception e) {
				return (new StandardJsonMessage(0, null, null, "Save Gagal" + e.getMessage()));
			}
		} catch (Exception e) {
			return (new StandardJsonMessage(0, null, null, "Save Gagal" + e.getMessage()));
		}

	}
}
