package org.sscn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.sscn.dao.RefLokasiDao;
import org.sscn.dao.RefLokasiTestDao;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.DtPersyaratan;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.DtVerifikasiNok;
import org.sscn.persistence.entities.DtVerifikasiUlang;
import org.sscn.persistence.entities.RefLokasi;
import org.sscn.persistence.entities.RefLokasiTest;
import org.sscn.persistence.entities.RefPendidikan;
import org.sscn.services.PendidikanService;
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
	private RefLokasiDao refLokasiDao;
	
	@Inject
	private RefLokasiTestDao refLokasiTestDao;

	@Inject
	private VerfikasiService verifikasiServices;
	
	@Inject
	private PendidikanService pendidikanServices;

	@Inject
	private PersyaratanService persyaratanServices;
	
	@Inject
	private VerfikasiService verfikasiService;
	
	@RequestMapping(value = "/verifikasi.do", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model, HttpSession session) {
		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			request.setAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		int indexAndCount[] = new int[2]; 
		int numRow;	
		String snumRow = request.getParameter("numPage");
		if (snumRow != null && !snumRow.contentEquals("")){
			numRow = Integer.parseInt(snumRow);
		} else {
			numRow = 10;
		}
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		DtUser user = userService.findByProperty("username", userLogin.getUsername(), null).get(0);
		List<DtPendaftaran> pendaftars = dtPendaftaranDao.findByInstansi(user.getRefInstansi(),
				indexAndCount);				
		int count = dtPendaftaranDao.countByInstansi(user.getRefInstansi());
		List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
				user.getRefInstansi(), null, null);
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		List<RefLokasiTest> lokasis = refLokasiTestDao.findByProperty("refInstansi", user.getRefInstansi(), null);
		model.addAttribute("lokasis", lokasis);
		model.addAttribute("pendaftars", pendaftars);
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("numRow",numRow);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);
		model.addAttribute("persyaratans", persyaratans);
		return "verifikasi";
	}
	
	@RequestMapping(value = "/verifikasi.do", method = RequestMethod.POST)
	public String indexPost(HttpServletRequest request, ModelMap model, HttpSession session) {
		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			request.setAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		
		DtUser user = userService.findByProperty("username", userLogin.getUsername(), null).get(0);
		
		int indexAndCount[] = new int[2]; 
		int numRow = 10;		
		indexAndCount[0] = 1;
		int count;
		
		String numRowPerPage = request.getParameter("numPage");
		if (numRowPerPage != null && !numRowPerPage.contentEquals("")){
			numRow = Integer.parseInt(numRowPerPage);
		}
		
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		String searchPar = request.getParameter("searchPar");
		String noReg = "";
		Boolean searchBy = false;
		if (searchPar != null && !searchPar.contentEquals("")){			
			noReg = request.getParameter("no_reg");
			
			if (noReg != null && !noReg.contentEquals("")){
				searchBy = true;
			}
			model.addAttribute("no_reg", noReg);
		}
		
		List<DtPendaftaran> pendaftars;
		if (searchBy){
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("noRegister", noReg);
			pendaftars = dtPendaftaranDao.findByInstansiAndMap(user.getRefInstansi(), maps, 
					indexAndCount);				
			count = dtPendaftaranDao.countByInstansiAndMap(user.getRefInstansi(), maps);
		} else {
			pendaftars = dtPendaftaranDao.findByInstansi(user.getRefInstansi(),
					indexAndCount);				
			count = dtPendaftaranDao.countByInstansi(user.getRefInstansi());
		}
		
		List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
				user.getRefInstansi(), null, null);
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		

		List<RefLokasiTest> lokasis = refLokasiTestDao.findByProperty("refInstansi", user.getRefInstansi(), null);
		model.addAttribute("lokasis", lokasis);
		model.addAttribute("pendaftars", pendaftars);
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("numRow",numRow);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);
		model.addAttribute("persyaratans", persyaratans);
		return "verifikasi";
	}

	@RequestMapping(value = "/verifikasiSave.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage save(HttpServletRequest request, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null, "Save Gagal");
			return res;
		}
		
		
		String pendaftarId = request.getParameter("pendaftarId");
		
		try {
			DtUser user = userService.findByProperty("username", userLogin.getUsername(), null)
					.get(0);
			List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
					user.getRefInstansi(), null);

			Iterator<DtPersyaratan> iterator = persyaratans.iterator();
			List<DtPersyaratan> gagalPersyaratan = new ArrayList<DtPersyaratan>();
			String[] persyaratanIds = new String[persyaratans.size()]; 			
			if (request.getParameterValues("persyaratanIds[]") != null){
				persyaratanIds = request.getParameterValues("persyaratanIds[]");
				int found = 0;
				while (iterator.hasNext()) {
					DtPersyaratan dtPersyaratan = iterator.next();
					found = 0;
					for (int i = 0; i < persyaratanIds.length; i++) {
						if (Integer.parseInt(persyaratanIds[i]) == dtPersyaratan.getId()) {
							found++;
						}
					}
					if (found == 0) {
						gagalPersyaratan.add(dtPersyaratan);
					}
				}
			} else {
				gagalPersyaratan.addAll(persyaratans);
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
				return (new StandardJsonMessage(0, null, null, "Save Gagal1 " + e.getMessage()));
			}
		} catch (Exception e) {
			return (new StandardJsonMessage(0, null, null, "Save Gagal2 " + e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/getPendaftaran.do", method = RequestMethod.GET)
	@ResponseBody
	public StandardJsonMessage getUser(
			@RequestParam("id") String id, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Get Pendaftaran Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		try {
			DtPendaftaran infoPendaftar = dtPendaftaranDao.findUniqueByProperty("id",
					Long.parseLong(id), null, null);
			List<DtVerifikasiNok> verNoks = null;
			if (infoPendaftar.getStatus().equalsIgnoreCase("0")){
				verNoks = verifikasiServices.findVerifikasiNoksByPendaftar(infoPendaftar);
			}
			
			List<DtVerifikasiNok> rverNoks = new ArrayList<DtVerifikasiNok>();
			if (verNoks != null){
				Iterator<DtVerifikasiNok> iter = verNoks.iterator();
				while (iter.hasNext()){
					DtVerifikasiNok temp = iter.next();
					temp.getPendaftar().setFormasi(null);
					temp.setPendaftar(null);
					temp.setVerifikator(null);
					DtPersyaratan temPersyaratan = new DtPersyaratan();
					temPersyaratan.setId(temp.getPersyaratan().getId());
					temPersyaratan.setRefInstansi(null);
					temPersyaratan.setSyarat(temp.getPersyaratan().getSyarat());
					temPersyaratan.setUrutan(temp.getPersyaratan().getUrutan());
					temPersyaratan.setUser(null);
					
					temp.setPersyaratan(temPersyaratan);
					rverNoks.add(temp);
				}
			} 
			Map<String, Object> mob = new HashMap<String, Object>();
			if (rverNoks.size() > 0){
				mob.put("verNoks", rverNoks);
			} else {
				mob = null;
			}
			String pddkn = "";
			if (infoPendaftar.getPendidikan() != null){
				RefPendidikan pddknTemp = pendidikanServices.findById(infoPendaftar.getPendidikan()); 
				pddkn = pddknTemp.getNama();
			}
			infoPendaftar.setPendidikan(pddkn);
			infoPendaftar.setFormasi(null);
			
			if (infoPendaftar.getLokasiTest() != null){				
				infoPendaftar.setLokasiTest(infoPendaftar.getLokasiTest());
			} 
			
			res = new StandardJsonMessage(1, infoPendaftar, mob, "Get Pendaftaran Success");
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Get Pendaftaran Gagal " + ex.getMessage());
		}

		return res;
	}
	
	@RequestMapping(value = "/resetverifikasi.do", method = RequestMethod.GET)
	public String resetVerifikasi(HttpServletRequest request, ModelMap model, HttpSession session) {
		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			request.setAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		
		Boolean searchBy = false;
		
		int indexAndCount[] = new int[2]; 
		int numRow;	
		String snumRow = request.getParameter("numPage");
		if (snumRow != null && !snumRow.contentEquals("")){
			numRow = Integer.parseInt(snumRow);
		} else {
			numRow = 10;
		}
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		DtUser user = userService.findByProperty("username", userLogin.getUsername(), null).get(0);
		List<DtPendaftaran> pendaftars = null;
		int count = 0;
		String noReg = request.getParameter("no_reg");
		if (noReg != null && !noReg.contentEquals("")){
			searchBy = true;
			model.addAttribute("no_reg", noReg);
		}
		
		if (searchBy){
			//NOOP
			
		} else {
			pendaftars = dtPendaftaranDao.findReverifikasiByInstansi(user.getRefInstansi(),
					indexAndCount);				
			count = dtPendaftaranDao.countReverifikasiByInstansi(user.getRefInstansi());
		}
		
		List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
				user.getRefInstansi(), null, null);
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		
		model.addAttribute("pendaftars", pendaftars);
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("numRow",numRow);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);
		model.addAttribute("persyaratans", persyaratans);
		return "resetverifikasi";
	}
	
	@RequestMapping(value = "/resetverifikasi.do", method = RequestMethod.POST)
	public String resetVerifikasiPost(HttpServletRequest request, ModelMap model, HttpSession session) {
		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			request.setAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		
		Boolean searchBy = false;
		
		int indexAndCount[] = new int[2]; 
		int numRow;	
		String snumRow = request.getParameter("numPage");
		if (snumRow != null && !snumRow.contentEquals("")){
			numRow = Integer.parseInt(snumRow);
		} else {
			numRow = 10;
		}
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		DtUser user = userService.findByProperty("username", userLogin.getUsername(), null).get(0);
		List<DtPendaftaran> pendaftars = null;
		int count = 0;
		String noReg = request.getParameter("no_reg");
		if (noReg != null && !noReg.contentEquals("")){
			searchBy = true;
			model.addAttribute("no_reg", noReg);
		}
		
		if (searchBy){
			//NOOP
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("noRegister", noReg);
			pendaftars = dtPendaftaranDao.findReverifikasiByInstansiAndMap(user.getRefInstansi(), map, indexAndCount);	
			count = dtPendaftaranDao.countReverifikasiByInstansiAndMap(user.getRefInstansi(), map);
		} else {
			pendaftars = dtPendaftaranDao.findReverifikasiByInstansi(user.getRefInstansi(),
					indexAndCount);				
			count = dtPendaftaranDao.countReverifikasiByInstansi(user.getRefInstansi());
		}
		
		List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
				user.getRefInstansi(), null, null);
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		
		model.addAttribute("pendaftars", pendaftars);
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("numRow",numRow);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);
		model.addAttribute("persyaratans", persyaratans);
		return "resetverifikasi";
	}

	@RequestMapping(value = "/resetverifikasiById.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage resetVerifikasiById(
			@RequestParam("id") String id, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null,
					"Reset Status Pendaftaran Gagal");
			return res;
		}

		StandardJsonMessage res = null;
		try {
			DtPendaftaran infoPendaftar = dtPendaftaranDao.findUniqueByProperty("id",
					Long.parseLong(id), null, null);
			List<DtVerifikasiNok> verNoks = null;
			String persyaratan = "";
			String status = infoPendaftar.getStatus();
			
			if (status != null && status.equalsIgnoreCase("0")){
				verNoks = verifikasiServices.findVerifikasiNoksByPendaftar(infoPendaftar);
				if (verNoks != null){
					Iterator<DtVerifikasiNok> iter = verNoks.iterator();
					while (iter.hasNext()){
						DtVerifikasiNok temp = iter.next();
						persyaratan += temp.getPersyaratan().getId() + ";";						
					}
					persyaratan = persyaratan.substring(0, persyaratan.length() - 1);
				}				
			} 
			
			DtVerifikasiUlang verUlang = new DtVerifikasiUlang();
			verUlang.setPendaftar(infoPendaftar.getNoRegister());
			verUlang.setStatus(status);
			verUlang.setVerifikator(infoPendaftar.getUserValidate());
			verUlang.setUserReset(userLogin.getUsername());
			verUlang.setPersyaratan(persyaratan);
			
			infoPendaftar.setStatus("");
			infoPendaftar.setNoPeserta(null);
			Boolean result = verifikasiServices.verifikasiUlang(verUlang, infoPendaftar);
			if (result){
				res = new StandardJsonMessage(1, null, null, "Reset Pendaftaran Success");
			} else {
				res = new StandardJsonMessage(0, null, null, "Reset Data Pendaftar Gagal");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			res = new StandardJsonMessage(0, null, null, "Reset Data Pendaftar Gagal " + ex.getMessage());
		}

		return res;
	}

	@RequestMapping(value = "/verifikasiFilter.do", method = RequestMethod.GET)
	public String verifikasiFilterGet(HttpServletRequest request, ModelMap model, HttpSession session) {
		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			request.setAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		int indexAndCount[] = new int[2]; 
		int numRow;	
		String snumRow = request.getParameter("numPage");
		if (snumRow != null && !snumRow.contentEquals("")){
			numRow = Integer.parseInt(snumRow);
		} else {
			numRow = 10;
		}
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		DtUser user = userService.findByProperty("username", userLogin.getUsername(), null).get(0);
//		List<DtPendaftaran> pendaftars = dtPendaftaranDao.findByInstansi(user.getRefInstansi(),
//				indexAndCount);				
//		int count = dtPendaftaranDao.countByInstansi(user.getRefInstansi());
		List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
				user.getRefInstansi(), null, null);
		List<DtPendaftaran> pendaftars = null;
		int count = 0;
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		
		model.addAttribute("pendaftars", pendaftars);
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("numRow",numRow);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);
		model.addAttribute("persyaratans", persyaratans);
		return "rankingverifikasi";
	}
	
	@RequestMapping(value = "/verifikasiFilter.do", method = RequestMethod.POST)
	public String verifikasiFilter(HttpServletRequest request, ModelMap model, HttpSession session) {
		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			request.setAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		
		DtUser user = userService.findByProperty("username", userLogin.getUsername(), null).get(0);
		
		int indexAndCount[] = new int[2]; 
		int numRow = 10;		
		indexAndCount[0] = 1;
		int count;
		
		String numRowPerPage = request.getParameter("numPage");
		if (numRowPerPage != null && !numRowPerPage.contentEquals("")){
			numRow = Integer.parseInt(numRowPerPage);
		}
		
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		String searchPar = request.getParameter("searchPar");
		String tkPend = "";
		String fromUsia = "";
		String toUsia = "";
		String fromIPK = "";
		String toIPK = "";
		String jmlh = "";
		Boolean searchBy = false;
		if (searchPar != null && !searchPar.contentEquals("")){
			tkPend = request.getParameter("tkPendidikan");
			fromUsia = request.getParameter("fromUsia");
			toUsia = request.getParameter("toUsia");
			fromIPK = request.getParameter("fromIPK");
			toIPK = request.getParameter("toIPK");
			jmlh = request.getParameter("jumlahData");
			
			if (!tkPend.contentEquals("") || !fromUsia.contentEquals("") || !toUsia.contentEquals("") || !fromIPK.contentEquals("") || 
					!toIPK.contentEquals("") ){
				searchBy = true;
			}
			model.addAttribute("tkPend", tkPend);
			model.addAttribute("fromUsia", fromUsia);
			model.addAttribute("toUsia", toUsia);
			model.addAttribute("fromIPK", fromIPK);
			model.addAttribute("toIPK", toIPK);
			model.addAttribute("jumlahData", jmlh);
		} else {
			searchPar = "";
		}
		
		List<DtPendaftaran> pendaftars;
		if (searchBy){
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("tkPendidikan", tkPend);
			if (!fromUsia.contentEquals("")){
				maps.put("fromUsia", fromUsia);
			}
			if (!toUsia.contentEquals("")){
				maps.put("toUsia", toUsia);
			}
			if (!fromIPK.contentEquals("")){
				maps.put("fromIPK", fromIPK);
			}
			if (!toIPK.contentEquals("")){
				maps.put("toIPK", toIPK);
			}
			Boolean isJmlhData = false;
			if (jmlh != null && !jmlh.contentEquals("")){
				indexAndCount[1] = Integer.parseInt(jmlh);
			} else {
				isJmlhData = true;
			}
			pendaftars = dtPendaftaranDao.findByInstansiAndMapFilterVerify(user.getRefInstansi(), maps, 
					indexAndCount);				
			count = dtPendaftaranDao.countByInstansiAndMapFilterVerify(user.getRefInstansi(), maps);
			if (count >= indexAndCount[1] && !isJmlhData){
				count = indexAndCount[1];
			}			
		} else {
			pendaftars = dtPendaftaranDao.findByInstansi(user.getRefInstansi(),
					indexAndCount);				
			count = dtPendaftaranDao.countByInstansi(user.getRefInstansi());
		}
		
		List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
				user.getRefInstansi(), null, null);
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}
		model.addAttribute("searchPar",searchPar);
		model.addAttribute("tkPendidikan",tkPend);
		model.addAttribute("jumlahData",jmlh);
		model.addAttribute("fromUsia", fromUsia);
		model.addAttribute("toUsia",toUsia);
		model.addAttribute("fromIPK", fromIPK);
		model.addAttribute("toIPK",toIPK);	
		model.addAttribute("pendaftars", pendaftars);
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("numRow",numRow);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);
		model.addAttribute("persyaratans", persyaratans);
		return "rankingverifikasi";
	}
	
	@RequestMapping(value = "/verifikasiBatchSave.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage saveBatch(HttpServletRequest request, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null, "Save Gagal");
			return res;
		}
		
		
		String pendaftarId = request.getParameter("pendaftarId");
		
		try {
			DtUser user = userService.findByProperty("username", userLogin.getUsername(), null)
					.get(0);
			List<DtPersyaratan> persyaratans = persyaratanServices.findByProperty("refInstansi",
					user.getRefInstansi(), null);

			Iterator<DtPersyaratan> iterator = persyaratans.iterator();
			List<DtPersyaratan> gagalPersyaratan = new ArrayList<DtPersyaratan>();
			String[] persyaratanIds = new String[persyaratans.size()]; 			
			if (request.getParameterValues("persyaratanIds[]") != null){
				persyaratanIds = request.getParameterValues("persyaratanIds[]");
				int found = 0;
				while (iterator.hasNext()) {
					DtPersyaratan dtPersyaratan = iterator.next();
					found = 0;
					for (int i = 0; i < persyaratanIds.length; i++) {
						if (Integer.parseInt(persyaratanIds[i]) == dtPersyaratan.getId()) {
							found++;
						}
					}
					if (found == 0) {
						gagalPersyaratan.add(dtPersyaratan);
					}
				}
			} else {
				gagalPersyaratan.addAll(persyaratans);
			}
			
			String tkPend = request.getParameter("tkPendidikan");
			String fromUsia = request.getParameter("fromUsia");
			String toUsia = request.getParameter("toUsia");
			String fromIPK = request.getParameter("fromIPK");
			String toIPK = request.getParameter("toIPK");
			String jmlh = request.getParameter("jumlahData");
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("tkPendidikan", tkPend);
			if (!fromUsia.contentEquals("")){
				maps.put("fromUsia", fromUsia);
			}
			if (!toUsia.contentEquals("")){
				maps.put("toUsia", toUsia);
			}
			if (!fromIPK.contentEquals("")){
				maps.put("fromIPK", fromIPK);
			}
			if (!toIPK.contentEquals("")){
				maps.put("toIPK", toIPK);
			}
			int pcount;
			if (jmlh != null && !jmlh.contentEquals("")){
				pcount = Integer.parseInt(jmlh);
			} else {
				pcount = dtPendaftaranDao.countByInstansiAndMapFilterVerify(user.getRefInstansi(), maps);
			}			
			int indexAndCount[] = new int[2];
			indexAndCount[0] = 1;
			indexAndCount[1] = pcount;
			List<DtPendaftaran> pendaftars = dtPendaftaranDao.findByInstansiAndMapFilterVerify(user.getRefInstansi(), maps, 
					indexAndCount);				
			
			
//			DtPendaftaran pendaftar = dtPendaftaranDao.findUniqueByProperty("id",
//					Long.parseLong(pendaftarId), null, null);

			List<DtVerifikasiNok> verNoks = new ArrayList<DtVerifikasiNok>();
			Iterator<DtPendaftaran> iterator2 = pendaftars.iterator();
			Boolean resVer = false;
			while (iterator2.hasNext()) {
				DtPendaftaran pendaftar = iterator2.next();
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
				resVer = verifikasiServices.simpanHasilVerifikasi(pendaftar, verNoks);
			}
			
			
			
			//pendaftar.setUserValidate(user.getUsername());

			try {
				if (resVer) {
					return (new StandardJsonMessage(1, null, null, "Save Success"));
				} else {
					return (new StandardJsonMessage(0, null, null, "Save Gagal"));
				}

			} catch (Exception e) {
				return (new StandardJsonMessage(0, null, null, "Save Gagal1 " + e.getMessage()));
			}
		} catch (Exception e) {
			return (new StandardJsonMessage(0, null, null, "Save Gagal2 " + e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/setLokasiUjian.do", method = RequestMethod.POST)
	@ResponseBody
	public StandardJsonMessage saveLokasiTest(HttpServletRequest request, HttpSession session)
			throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			StandardJsonMessage res = new StandardJsonMessage(-1, null, null, "Save Gagal");
			return res;
		}
		
		
		String pendaftarId = request.getParameter("pendaftarId");
		String lokasiId = request.getParameter("lokasi");
		
		try {
			//DtUser user = userService.findByProperty("username", userLogin.getUsername(), null)
					//.get(0);
			
			DtPendaftaran pendaftar = dtPendaftaranDao.findUniqueByProperty("id",
					Long.parseLong(pendaftarId), null, null);
			
			//RefLokasiTest lokasiTest = refLokasiTestDao.findById(lokasiId);
			
			try {
				pendaftar.setLokasiTest(lokasiId);	
				DtPendaftaran resVer = verfikasiService.update(pendaftar);
				

				if (resVer != null) {
					return (new StandardJsonMessage(1, null, null, "Save Success"));
				} else {
					return (new StandardJsonMessage(0, null, null, "Save Gagal"));
				}

			} catch (Exception e) {
				return (new StandardJsonMessage(0, null, null, "Save Gagal1 " + e.getMessage()));
			}
		} catch (Exception e) {
			return (new StandardJsonMessage(0, null, null, "Save Gagal2 " + e.getMessage()));
		}
	}
}
