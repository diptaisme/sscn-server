package org.sscn.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.sscn.core.persistence.tools.QueryOrder;
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
import org.sscn.services.FormasiService;

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

		List<MFormasi> formasis = mFormasiDao.findByProperty("refInstansi", userp.getRefInstansi(), orders, indexAndCount);
		Integer count = mFormasiDao.countByProperty("refInstansi", userp.getRefInstansi());
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);		
		model.addAttribute("formasis", formasis);
//		RefLokasi lastInputLokasi;
//		MFormasi forms = formasis.get(formasis.size()-1);
//		lastInputLokasi = forms.getRefLokasi();
//		model.addAttribute("lastInputLokasi", lastInputLokasi);
		
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
			model.addAttribute("pesan", "Session habis silahkan login kembali");
			return "login";
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
			model.addAttribute("pesan", "update berhasil");
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("pesan", "update gagal");
		}
		return "redirect:formasi.do";
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
		
		List<MFormasi> formasis = mFormasiDao.findByProperty("refInstansi", userp.getRefInstansi(), orders, indexAndCount);
		Integer count = mFormasiDao.countByProperty("refInstansi", userp.getRefInstansi());
		
		int numPage = (int) Math.ceil((double)count/indexAndCount[1]);		
		int activePage = (int) Math.ceil((double)(indexAndCount[0] + 1)/ indexAndCount[1]);
		int part2;
		if ((activePage * indexAndCount[1]) >= count){
			part2 = count;
		} else {
			part2 = activePage * indexAndCount[1];
		}		
		
		model.addAttribute("count",count);
		model.addAttribute("part2", part2);
		model.addAttribute("numpage",numPage);
		model.addAttribute("indexAndCount", indexAndCount);
		model.addAttribute("activePage", activePage);		
		model.addAttribute("formasis", formasis);

		try {
			MFormasi nformasi = mFormasiDao.findUniqueByProperty("id", Integer.parseInt(id), null, null);
			model.addAttribute("formasi", nformasi);
		} catch (Exception ex) {
			ex.printStackTrace();
			model.addAttribute("pesan", "Insert gagal");
		}

		return "formasi";
	}
}
