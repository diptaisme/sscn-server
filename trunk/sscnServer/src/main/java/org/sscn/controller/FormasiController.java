package org.sscn.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.mapping.Array;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.sscn.core.persistence.tools.PropCriteriaAndValue;
import org.sscn.core.persistence.tools.QueryComparator;
import org.sscn.core.persistence.tools.QueryOrder;
import org.sscn.dao.DtUserDao;
import org.sscn.dao.MFormasiDao;
import org.sscn.dao.RefJabatanDao;
import org.sscn.dao.RefJenisFormasiDao;
import org.sscn.dao.RefLokasiDao;
import org.sscn.dao.RefPendidikanDao;
import org.sscn.persistence.entities.DtFormasi;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.MFormasi;
import org.sscn.persistence.entities.RefJabatan;
import org.sscn.persistence.entities.RefJenisFormasi;
import org.sscn.persistence.entities.RefLokasi;
import org.sscn.persistence.entities.RefPendidikan;
import org.sscn.services.FormasiService;

@Controller
public class FormasiController {
	

	@Inject
	private RefLokasiDao refLokasiDao;

	@Inject
	private RefJabatanDao refJabatanDao;

	@Inject
	private RefPendidikanDao refPendidikanDao;

	@Inject
	private RefJenisFormasiDao refJenisFormasiDao;
	
	@Inject
	private MFormasiDao mFormasiDao;

	@Inject
	private DtUserDao dtUserDao;
	
	@Inject
	private FormasiService formasiService;

	@RequestMapping(value = "/formasi.do", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session, HttpServletRequest request) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		if (user.getKewenangan().equalsIgnoreCase("3")) {
			model.addAttribute("pesan", "Anda tidak berhak mengakses halaman ini");
			return "login";
		}

		model.addAttribute("username", user.getNama());
		DtUser userp = dtUserDao.findById(user.getUsername());
		model.addAttribute("instansiNama", userp.getRefInstansi().getNama());
		String pesan = request.getParameter("pesan");
		if (pesan != null){
			model.addAttribute("pesan", pesan);
		}
		
		
		int indexAndCount[] = new int[2]; 
		int numRow = 10;		
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		List<QueryOrder> orders = new ArrayList<QueryOrder>();
		orders.add(new QueryOrder("refLokasi.kode"));
		orders.add(new QueryOrder("refJabatan.nama"));

//		List<MFormasi> formasis = mFormasiDao.findByProperty("refInstansi", userp.getRefInstansi(), orders, indexAndCount);
//		Integer count = mFormasiDao.countByProperty("refInstansi", userp.getRefInstansi());
		List<MFormasi> formasis = mFormasiDao.findFormasiUmum(user.getRefInstansi().getKode(), orders, indexAndCount);
		Integer count = mFormasiDao.countFormasiUmum(userp.getRefInstansi().getKode());
		Integer jmlFormasi = 0;	
		if (count > 0){
			jmlFormasi = mFormasiDao.countNumberOfFormasiUmum(userp.getRefInstansi().getKode());
		} 
		
		model.addAttribute("jmlFormasi", jmlFormasi);
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		Boolean isDaerah = false;
		if (Integer.parseInt(user.getRefInstansi().getKode()) >= 5000){
			isDaerah = true;
		}
		List<RefLokasi> lokasis = refLokasiDao.findByProperty("refInstansi", user.getRefInstansi(), null);
		model.addAttribute("lokasis", lokasis);
		model.addAttribute("isDaerah",isDaerah);
		if (isDaerah){
			if (lokasis.size() > 0){
				RefLokasi lok = lokasis.get(0);
				model.addAttribute("kodeLokasi",lok.getKode());
			}			
		}
		
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);		
		model.addAttribute("formasis", formasis);
		
		return "formasi";
	}

	@RequestMapping(value = "/formasi.do", method = RequestMethod.POST)
	public String indexPost(ModelMap model, HttpSession session, HttpServletRequest request) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		if (user.getKewenangan().equalsIgnoreCase("3")) {
			model.addAttribute("pesan", "Anda tidak berhak mengakses halaman ini");
			return "login";
		}

		model.addAttribute("username", user.getNama());
		DtUser userp = dtUserDao.findById(user.getUsername());
		model.addAttribute("instansiNama", userp.getRefInstansi().getNama());
		String pesan = request.getParameter("pesan");
		if (pesan != null){
			model.addAttribute("pesan", pesan);
		}
		
		
		int indexAndCount[] = new int[2]; 
		int numRow = 10;		
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		List<QueryOrder> orders = new ArrayList<QueryOrder>();
		orders.add(new QueryOrder("refLokasi.kode"));
		orders.add(new QueryOrder("refJabatan.nama"));

		String searchPar = request.getParameter("searchPar");
		String lokasi = "";
		String namaJabatan = "";
		Boolean searchBy = false;
		if (searchPar != null && !searchPar.contentEquals("")){			
			lokasi = request.getParameter("slokasi");
			namaJabatan = request.getParameter("namaJabatan");
			if (lokasi != null && !lokasi.contentEquals("")){
				searchBy = true;
			}
			
			if (namaJabatan != null && !namaJabatan.contentEquals("")){
				searchBy = true;
			}
			model.addAttribute("lokasi", lokasi);
			model.addAttribute("namaJabatan", namaJabatan);
		}
		
		List<MFormasi> formasis;
		Integer count; 
		if (searchBy){
			List<PropCriteriaAndValue> filterList = new ArrayList<PropCriteriaAndValue>();
//			
			if (namaJabatan!=null && namaJabatan.equalsIgnoreCase("")){
//				namaJabatan = null;	
			}			
//			
			formasis = mFormasiDao.findFormasiUmumByLokasiAndNamaJabatan(lokasi, namaJabatan, orders, indexAndCount);
			count = mFormasiDao.countFormasiUmumByLokasiAndNamaJabatan(lokasi, namaJabatan);
		} else {
			formasis = mFormasiDao.findByProperty("refInstansi", userp.getRefInstansi(), orders, indexAndCount);
			count = mFormasiDao.countByProperty("refInstansi", userp.getRefInstansi());
		}
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
				
		Boolean isDaerah = false;
		if (Integer.parseInt(user.getRefInstansi().getKode()) >= 5000){
			isDaerah = true;
		}
		List<RefLokasi> lokasis = refLokasiDao.findByProperty("refInstansi", user.getRefInstansi(), null);
		if (isDaerah){
			if (lokasis.size() > 0){
				model.addAttribute("kodeLokasi",lokasis.get(0).getKode());
			}			
		}
		
		Integer jmlFormasi = 0;	
		if (count > 0){
			jmlFormasi = mFormasiDao.countNumberOfFormasiUmum(userp.getRefInstansi().getKode());
		} 
		
		model.addAttribute("jmlFormasi", jmlFormasi);
		
		model.addAttribute("isDaerah",isDaerah);
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);		
		model.addAttribute("formasis", formasis);
		model.addAttribute("lokasis", lokasis);
		model.addAttribute("lokasi", lokasi);
		model.addAttribute("namaJabatan", namaJabatan);
		
		return "formasi";
	}

	
	@RequestMapping(value = "/formasiSave.do", method = RequestMethod.POST)
	public String save(@RequestParam("lokasi") String lokasi,
			@RequestParam("jenisFormasi") String jenisFormasi,
			@RequestParam("jabatan") String jabatan,
			@RequestParam("pendidikan[]") String[] pendidikan,
			@RequestParam("pendidikanJmlh[]") String[] pendidikanJml,
			@RequestParam("jumlah") String jumlah, HttpSession session,
			ModelMap model) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		HashMap<String,Object> propertiesMap = new HashMap<String, Object>();
		MFormasi nformasi = new MFormasi();
		RefLokasi refLokasi = refLokasiDao.findById(lokasi);
		nformasi.setRefLokasi(refLokasi);

		RefJabatan refJabatan = refJabatanDao.findById(jabatan);
		nformasi.setRefJabatan(refJabatan);
		
		RefJenisFormasi refJenisFormasi= refJenisFormasiDao.findById(Integer.valueOf(jenisFormasi));
		nformasi.setRefJenisFormasi(refJenisFormasi);

		propertiesMap.put("refJabatan", refJabatan);
		propertiesMap.put("refLokasi", refLokasi);
		List<MFormasi> checkFormasi = mFormasiDao.findByMapOfProperties(propertiesMap, null, null);
		
		if (checkFormasi.size() > 0){
			model.addAttribute("pesan", "Insert gagal, Nama Jabatan dan Lokasi yang dimasukkan telah ada didatabase");
			return "redirect:formasi.do";
		}
		
		try {
			
			DtUser user = dtUserDao.findById(userLogin.getUsername());
			nformasi.setRefInstansi(user.getRefInstansi());

			nformasi.setJumlah(Integer.parseInt(jumlah));
			Set<DtFormasi> dtFormasis = new HashSet<DtFormasi>();	
			for (int i = 0; i < pendidikan.length; i++) {
				DtFormasi detFormasi = new DtFormasi(nformasi);
				RefPendidikan objPendidikan = refPendidikanDao
						.findById(pendidikan[i]);
				detFormasi.setPendidikan(objPendidikan);
				detFormasi.setJumlah(Integer.parseInt(pendidikanJml[i]));
				dtFormasis.add(detFormasi);
			}
			nformasi.setDtFormasis(dtFormasis);
			formasiService.insertFormasi(nformasi);
			model.addAttribute("pesan", "Data formasi berhasil ditambahkan");
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("pesan", "Data formasi gagal ditambahkan");
		}
		
		if (jenisFormasi.equalsIgnoreCase("1")){
			return "redirect:formasi.do";
		} else {
			return "redirect:formasiKhusus.do";
		}
		
	}

	// roberto
	@RequestMapping(value = "/formasiUpdate.do", method = RequestMethod.POST)
	public String update(@RequestParam("id") String id,
			@RequestParam("lokasi") String lokasi,
			@RequestParam("jenisFormasi") String jenisFormasi,
			@RequestParam("jabatan") String jabatan,
			@RequestParam("pendidikan[]") String[] pendidikan,
			@RequestParam("pendidikanJmlh[]") String[] pendidikanJml,
			@RequestParam("jumlah") String jumlah, HttpSession session,
			ModelMap model) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}

		try {
			MFormasi nformasi = mFormasiDao.findUniqueByProperty("id", Integer.parseInt(id), null, null);
			MFormasi oldFormasi = mFormasiDao.findUniqueByProperty("id", Integer.parseInt(id), null, null);
			RefLokasi refLokasi = refLokasiDao.findById(lokasi);
			nformasi.setRefLokasi(refLokasi);

			RefJabatan refJabatan = refJabatanDao.findById(jabatan);
			nformasi.setRefJabatan(refJabatan);

			DtUser user = dtUserDao.findById(userLogin.getUsername());
			nformasi.setRefInstansi(user.getRefInstansi());

			RefJenisFormasi refJenisFormasi= refJenisFormasiDao.findById(Integer.valueOf(jenisFormasi));
			nformasi.setRefJenisFormasi(refJenisFormasi);
			nformasi.setJumlah(Integer.parseInt(jumlah));

			//Iterator<DtFormasi> iterator = nformasi.getDtFormasis().iterator();
			Set<DtFormasi> dtFormasis = new HashSet<DtFormasi>();
			for (int i = 0; i < pendidikan.length; i++) {
				DtFormasi detFormasi = new DtFormasi(nformasi);
				RefPendidikan objPendidikan = refPendidikanDao
						.findById(pendidikan[i]);
				detFormasi.setPendidikan(objPendidikan);
				detFormasi.setJumlah(Integer.parseInt(pendidikanJml[i]));
				dtFormasis.add(detFormasi);
			}
			
//			nformasi.setDtFormasis(dtFormasis);
			formasiService.updateFormasi(oldFormasi, dtFormasis);
			model.addAttribute("pesan", "Data formasi berhasil disimpan");
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("pesan", "Data formasi gagal disimpan");
		}
		
		if (jenisFormasi.equalsIgnoreCase("1")){
			return "redirect:formasi.do";
		} else {
			return "redirect:formasiKhusus.do";
		}
	}

	@RequestMapping(value = "/formasiDelete.do", method = RequestMethod.GET)
	public String delete(@RequestParam("id") String id, HttpSession session,
			ModelMap model) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}

		try {
			formasiService.deleteFormasi(Integer.parseInt(id));
			model.addAttribute("pesan", "delete berhasil");
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("pesan", "delete gagal " + ex.getMessage());
		}

		return "redirect:formasi.do";
	}
	
	@RequestMapping(value = "/getFormasi.do", method = RequestMethod.GET)
	public String getFormasi(HttpServletRequest request, HttpSession session,
			ModelMap model) throws Exception {

		DtUser userLogin = (DtUser) session.getAttribute("userLogin");
		if (userLogin == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		String id = request.getParameter("id");
		model.addAttribute("username", userLogin.getNama());
		DtUser userp = dtUserDao.findById(userLogin.getUsername());
		model.addAttribute("instansiNama", userp.getRefInstansi().getNama());
		int indexAndCount[] = new int[2]; 
		int numRow = 10;		
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		List<QueryOrder> orders = new ArrayList<QueryOrder>();
		orders.add(new QueryOrder("refLokasi.kode"));
		orders.add(new QueryOrder("refJabatan.nama"));
		
		String jsp = "formasi";
		MFormasi nformasi = mFormasiDao.findUniqueByProperty("id", Integer.parseInt(id), null, null);
		List<MFormasi> formasis;
		Integer count;
		String jf = nformasi.getRefJenisFormasi().getNama();
		if (!jf.equalsIgnoreCase("UMUM")){
			jsp = "formasikhusus";
			List<PropCriteriaAndValue> xfil = new ArrayList<PropCriteriaAndValue>();
			xfil.add(new PropCriteriaAndValue("id",QueryComparator.NOT_EQUAL,1));
			List<RefJenisFormasi> jenisFormasis = refJenisFormasiDao.findUsingFilter(xfil,null);
			model.addAttribute("jenisFormasis",jenisFormasis);
			formasis = mFormasiDao.findFormasiKhusus(userp.getRefInstansi().getKode(), orders, indexAndCount);
			count = mFormasiDao.countFormasiKhusus(userp.getRefInstansi().getKode());
		} else {
			formasis = mFormasiDao.findFormasiUmum(userp.getRefInstansi().getKode(), orders, indexAndCount);
			count = mFormasiDao.countFormasiUmum(userp.getRefInstansi().getKode());
		}
		model.addAttribute("formasi", nformasi);
		
//		List<MFormasi> formasis = mFormasiDao.findByProperty("refInstansi", userp.getRefInstansi(), orders, indexAndCount);
//		Integer count = mFormasiDao.countByProperty("refInstansi", userp.getRefInstansi());
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		
		Boolean isDaerah = false;
		if (Integer.parseInt(userp.getRefInstansi().getKode()) >= 5000){
			isDaerah = true;
		}
		List<RefLokasi> lokasis = refLokasiDao.findByProperty("refInstansi", userp.getRefInstansi(), null);
		if (isDaerah){
			if (lokasis.size() > 0){
				model.addAttribute("kodeLokasi",lokasis.get(0).getKode());
			}			
		}
		model.addAttribute("isDaerah",isDaerah);
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);		
		model.addAttribute("formasis", formasis);
		
		
		
		return jsp;
	}
	
	@RequestMapping(value = "/formasiKhusus.do", method = RequestMethod.GET)
	public String indexFormasiKhusus(ModelMap model, HttpSession session, HttpServletRequest request) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		if (user.getKewenangan().equalsIgnoreCase("3")) {
			model.addAttribute("pesan", "Anda tidak berhak mengakses halaman ini");
			return "login";
		}

		model.addAttribute("username", user.getNama());
		DtUser userp = dtUserDao.findById(user.getUsername());
		model.addAttribute("instansiNama", userp.getRefInstansi().getNama());
		String pesan = request.getParameter("pesan");
		if (pesan != null){
			model.addAttribute("pesan", pesan);
		}
		
		int indexAndCount[] = new int[2]; 
		int numRow = 10;		
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		List<QueryOrder> orders = new ArrayList<QueryOrder>();
		orders.add(new QueryOrder("refLokasi.kode"));
		orders.add(new QueryOrder("refJabatan.nama"));
		
		List<MFormasi> formasis = mFormasiDao.findFormasiKhusus(userp.getRefInstansi().getKode(), orders, indexAndCount);
		Integer count = mFormasiDao.countFormasiKhusus(userp.getRefInstansi().getKode());
//		Integer count = mFormasiDao.countByProperty("refInstansi", userp.getRefInstansi());
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		Boolean isDaerah = false;
		if (Integer.parseInt(user.getRefInstansi().getKode()) >= 5000){
			isDaerah = true;
		}
		List<RefLokasi> lokasis = refLokasiDao.findByProperty("refInstansi", user.getRefInstansi(), null);
		model.addAttribute("lokasis", lokasis);
		model.addAttribute("isDaerah",isDaerah);
		if (isDaerah){
			if (lokasis.size() > 0){
				RefLokasi lok = lokasis.get(0);
				model.addAttribute("kodeLokasi",lok.getKode());
			}			
		}
		
		List<PropCriteriaAndValue> xfil = new ArrayList<PropCriteriaAndValue>();
		xfil.add(new PropCriteriaAndValue("id",QueryComparator.NOT_EQUAL,1));
		List<RefJenisFormasi> jenisFormasis = refJenisFormasiDao.findUsingFilter(xfil,null);
		model.addAttribute("jenisFormasis",jenisFormasis);
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);		
		model.addAttribute("formasis", formasis);
		
		return "formasikhusus";
	}

	@RequestMapping(value = "/formasiKhusus.do", method = RequestMethod.POST)
	public String indexFormasiKhususPost(ModelMap model, HttpSession session, HttpServletRequest request) {
		DtUser user = (DtUser) session.getAttribute("userLogin");
		if (user == null) {
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
		}
		if (user.getKewenangan().equalsIgnoreCase("3")) {
			model.addAttribute("pesan", "Anda tidak berhak mengakses halaman ini");
			return "login";
		}

		model.addAttribute("username", user.getNama());
		DtUser userp = dtUserDao.findById(user.getUsername());
		model.addAttribute("instansiNama", userp.getRefInstansi().getNama());
		String pesan = request.getParameter("pesan");
		if (pesan != null){
			model.addAttribute("pesan", pesan);
		}	
		
		int indexAndCount[] = new int[2]; 
		int numRow = 10;		
		indexAndCount[0] = 1;
		String index = request.getParameter("activePage");
		if (index != null && !index.contentEquals("")){
			indexAndCount[0] = Integer.parseInt(index);			
		} 
		indexAndCount[0] = (indexAndCount[0] - 1) * numRow;			
		indexAndCount[1] = numRow;
		
		List<QueryOrder> orders = new ArrayList<QueryOrder>();
		orders.add(new QueryOrder("refLokasi.kode"));
		orders.add(new QueryOrder("refJabatan.nama"));

		String searchPar = request.getParameter("searchPar");
		String lokasi = "";
		String namaJabatan = "";
		String jenisFormasi = "";
		Boolean searchBy = false;
		if (searchPar != null && !searchPar.contentEquals("")){			
			lokasi = request.getParameter("slokasi");
			namaJabatan = request.getParameter("namaJabatan");
			jenisFormasi = request.getParameter("sjenisFormasi");
			if (lokasi != null && !lokasi.contentEquals("")){
				searchBy = true;
			}
			
			if (namaJabatan != null && !namaJabatan.contentEquals("")){
				searchBy = true;
			}
			
			if (jenisFormasi != null && !jenisFormasi.contentEquals("")){
				searchBy = true;
			}
			model.addAttribute("slokasi", lokasi);
			model.addAttribute("namaJabatan", namaJabatan);
			model.addAttribute("sjenisFormasi", jenisFormasi);
		}
		
		List<MFormasi> formasis;
		Integer count; 
		if (searchBy){
			List<PropCriteriaAndValue> filterList = new ArrayList<PropCriteriaAndValue>();
			filterList.add(new PropCriteriaAndValue("refInstansi.kode", QueryComparator.EQUALS, userp.getRefInstansi().getKode()));
			
			if (lokasi!=null && !lokasi.equalsIgnoreCase("")){
				filterList.add(new PropCriteriaAndValue("refLokasi.kode", QueryComparator.EQUALS, lokasi));
			}
			if (namaJabatan!=null && !namaJabatan.equalsIgnoreCase("")){
				filterList.add(new PropCriteriaAndValue("refJabatan.nama", QueryComparator.LIKE, "%"+namaJabatan+"%"));
			}
			if (jenisFormasi!=null && !jenisFormasi.equalsIgnoreCase("")){
				filterList.add(new PropCriteriaAndValue("refJenisFormasi.id", QueryComparator.EQUALS, Integer.valueOf(jenisFormasi)));
			}
			formasis = mFormasiDao.findFormasiKhususUsingFilter(filterList, orders, indexAndCount);
			count = mFormasiDao.countFormasiKhususUsingFilter(filterList);
		} else {
			formasis = mFormasiDao.findFormasiKhusus(userp.getRefInstansi().getKode(), orders, indexAndCount);
			count = mFormasiDao.countFormasiKhusus(userp.getRefInstansi().getKode());
		}
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
				
		Boolean isDaerah = false;
		if (Integer.parseInt(user.getRefInstansi().getKode()) >= 5000){
			isDaerah = true;
		}
		List<RefLokasi> lokasis = refLokasiDao.findByProperty("refInstansi", user.getRefInstansi(), null);
		if (isDaerah){
			if (lokasis.size() > 0){
				model.addAttribute("kodeLokasi",lokasis.get(0).getKode());
			}			
		}
		
		List<PropCriteriaAndValue> xfil = new ArrayList<PropCriteriaAndValue>();
		xfil.add(new PropCriteriaAndValue("id",QueryComparator.NOT_EQUAL,1));
		List<RefJenisFormasi> jenisFormasis = refJenisFormasiDao.findUsingFilter(xfil,null);
		model.addAttribute("jenisFormasis",jenisFormasis);
		model.addAttribute("isDaerah",isDaerah);
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);		
		model.addAttribute("formasis", formasis);
		model.addAttribute("lokasis", lokasis);
		model.addAttribute("lokasi", lokasi);
		model.addAttribute("namaJabatan", namaJabatan);
		
		return "formasikhusus";
	}
}
