package org.sscn.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sscn.model.json.JabatanJson;
import org.sscn.model.json.LokasiJson;
import org.sscn.model.json.PendidikanJson;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.MFormasi;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.persistence.entities.RefJabatan;
import org.sscn.persistence.entities.RefLokasi;
import org.sscn.persistence.entities.RefPendidikan;
import org.sscn.services.RegistrasiService;

@Controller
public class RegistrasiController {
	@Inject
	private RegistrasiService registrasiService;

	// belum selesai
	@RequestMapping(value = "/registrasi.do", method = RequestMethod.POST)
	public String registrasi(@RequestParam("no_ktp") String noKtp,
			@RequestParam("nama") String nama,
			@RequestParam("tempat_lahir") String tempatLahir,
			@RequestParam("tglMonth") String tglMonth,
			@RequestParam("tglDay") String tglDay,
			@RequestParam("tglYear") String tglYear,
			@RequestParam("alamat") String alamat,
			@RequestParam("kota") String kota,
			@RequestParam("propinsi") String propinsi,
			@RequestParam("kode_pos") String kodePos,
			@RequestParam("area_telpon") String areaTelpon,
			@RequestParam("nomor_telpon") String nomorTelpon,
			@RequestParam("email") String email,
			@RequestParam("formasi") String formasi,
			@RequestParam("pendidikan") String pendidikan,
			@RequestParam("no_ijazah") String noIjazah, Model model) {
		DtPendaftaran pendaftaran = new DtPendaftaran();
		pendaftaran.setAlamat(alamat);
		pendaftaran.setJnsKelamin("P");
		pendaftaran.setKeterangan("sdsadsa keterangan");
		pendaftaran.setLembaga("lem");
		pendaftaran.setFormasi(new MFormasi());
		pendaftaran.setLokasiTest("lokT");

		int minute = Calendar.getInstance().getTime().getMinutes();
		int day = Calendar.getInstance().getTime().getDay();
		int second = Calendar.getInstance().getTime().getSeconds();
		int x = noKtp.length() > 7 ? 2 : 3;
		int y = noKtp.length() % 2 > 7 ? 0 : 1;
		String noPeserta = "" + x + minute + "0" + day + y + second; // random
																		// example
		String noRegister = "" + y + day + minute + x + second; // random
																// example
		if (noPeserta.length() > 10) {
			noPeserta = noPeserta.substring(0, 10);
		}
		if (noRegister.length() > 10) {
			noRegister = noRegister.substring(0, 10);
		}
		pendaftaran.setNoPeserta(noPeserta);
		pendaftaran.setNoRegister(noRegister); // example
		pendaftaran.setStatus("V");
		pendaftaran.setTglTest(new Date());
		pendaftaran.setTglUpdated(new Date());
		pendaftaran.setTglValidate(new Date());
		pendaftaran.setUserValidate("kosong");

		pendaftaran.setEmail(email);
		pendaftaran.setKodePos(kodePos);
		pendaftaran.setKota(kota);
		pendaftaran.setNama(nama);
		pendaftaran.setNoIjazah(noIjazah);
		pendaftaran.setNoNik(noKtp);
		pendaftaran.setPendidikan(pendidikan);
		pendaftaran.setPropinsi(propinsi);
		pendaftaran.setTelpon(areaTelpon + nomorTelpon);
		pendaftaran.setTglCreated(Calendar.getInstance().getTime());
		pendaftaran.setTmpLahir(tempatLahir);
		// dd-MM-yyyy
		String strDate = tglDay + "-" + "10" + "-" + tglYear;
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date tglLahir = null;
		try {
			tglLahir = formatter.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pendaftaran.setTglLahir(tglLahir);

		pendaftaran = registrasiService.insertNewRegistrasi(pendaftaran);

		model.addAttribute("pendaftaran", pendaftaran);
		return "RegistrasiSuccess";
	}

	private String generateNoRegistrasi() {
		return "";
	}

	@RequestMapping(value = "/ac_instansi.do", method = RequestMethod.GET)
	@ResponseBody
	public String findInstansiLikeByName(
			@RequestParam("callback") String callBack,
			@RequestParam("maxRows") String maxRows,
			@RequestParam("startsWith") String startsWith) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<RefInstansi> instansis = registrasiService.getInstansi(
				Integer.parseInt(maxRows), startsWith);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("instansis", instansis);
		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				resultMap));
	}

	/*@RequestMapping(value = "/cb_lokasi_by_instansi.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<LokasiJson>> getLokasis(
			@RequestParam("instansi") String instansi) {

		List<RefLokasi> lokasis = registrasiService.getLokasi(instansi);
		Map<String, RefLokasi> mapLokasis = new Hashtable<String, RefLokasi>();
		for (RefLokasi lokasi : lokasis) {
			if(!mapLokasis.containsKey(lokasi.getKode())){
				mapLokasis.put(lokasi.getKode(), lokasi);
			}
		}
		lokasis = new ArrayList<RefLokasi>(mapLokasis.values());
		List<LokasiJson> newLokasis = new ArrayList<LokasiJson>();
		for (RefLokasi lokasi : lokasis) {
			// lokasi.setRefInstansi(null);http://localhost:8080/sscnServer/dashboard.dohttp://localhost:8080/sscnServer/dashboard.do
			newLokasis.add(new LokasiJson(lokasi.getKode(), lokasi.getNama()));
		}
		Map<String, List<LokasiJson>> lokasiMap = new HashMap<String, List<LokasiJson>>();
		lokasiMap.put("lokasis", newLokasis);

		return lokasiMap;
	}*/
	@RequestMapping(value = "/cb_lokasi_by_instansi.do", method = RequestMethod.GET)
	@ResponseBody
	public String getLokasis(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi) throws Exception{
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<RefLokasi> lokasis = registrasiService.getLokasi(instansi);

		Map<String, RefLokasi> mapLokasis = new Hashtable<String, RefLokasi>();
		for (RefLokasi lokasi : lokasis) {
			if(!mapLokasis.containsKey(lokasi.getKode())){
				mapLokasis.put(lokasi.getKode(), lokasi);
			}
		}
		lokasis = new ArrayList<RefLokasi>(mapLokasis.values());
		List<LokasiJson> newLokasis = new ArrayList<LokasiJson>();
		for (RefLokasi lokasi : lokasis) {
			// lokasi.setRefInstansi(null);http://localhost:8080/sscnServer/dashboard.dohttp://localhost:8080/sscnServer/dashboard.do
			newLokasis.add(new LokasiJson(lokasi.getKode(), lokasi.getNama()));
		}
		Map<String, List<LokasiJson>> lokasiMap = new HashMap<String, List<LokasiJson>>();
		lokasiMap.put("lokasis", newLokasis);
		
		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				lokasiMap));
	}


	/*
	 * @RequestMapping(value = "/cb_lokasi_by_instansi.do", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public Map<String, List<RefLokasi>> getLokasis(
	 * 
	 * @RequestParam("instansi") String instansi) {
	 * 
	 * List<RefLokasi> lokasis = registrasiService.getLokasi(instansi);
	 * List<RefLokasi> newLokasis = new ArrayList<RefLokasi>(); for (RefLokasi
	 * lokasi : lokasis) { lokasi.setRefInstansi(null); newLokasis.add(lokasi);
	 * } Map<String, List<RefLokasi>> lokasiMap = new HashMap<String,
	 * List<RefLokasi>>(); lokasiMap.put("lokasis", newLokasis);
	 * 
	 * return lokasiMap; }
	 */

	/*
	 * @RequestMapping(value = "/cb_lokasi_by_instansi.do", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public String getLokasis(@RequestParam("callback") String
	 * callBack,
	 * 
	 * @RequestParam("instansi") String instansi) throws Exception {
	 * ObjectMapper objectMapper = new ObjectMapper(); List<RefLokasi>
	 * refLokasis = registrasiService.getLokasi(instansi); List<LokasiJson>
	 * lokasis = new ArrayList<LokasiJson>(); for (RefLokasi refLokasi :
	 * refLokasis) { lokasis.add(new LokasiJson(refLokasi.getKode(),
	 * refLokasi.getNama())); } Map<String, Object> lokasiMap = new
	 * HashMap<String, Object>(); lokasiMap.put("lokasis", lokasis); return
	 * objectMapper.writeValueAsString(new JSONPObject(callBack, lokasiMap)); }
	 */

	/*
	 * @RequestMapping(value = "/cb_jabatan_by_instansi_lokasi.do", method =
	 * RequestMethod.GET)
	 * 
	 * @ResponseBody public Map<String, List<RefJabatan>> getJabatans(
	 * 
	 * @RequestParam("instansi") String instansi,
	 * 
	 * @RequestParam("lokasi") String lokasi) {
	 * 
	 * List<RefJabatan> jabatans = registrasiService.getJabatan(instansi,
	 * lokasi); Map<String, List<RefJabatan>> lokasiMap = new HashMap<String,
	 * List<RefJabatan>>(); lokasiMap.put("jabatans", jabatans); return
	 * lokasiMap; }
	 */

	/*@RequestMapping(value = "/cb_jabatan_by_instansi_lokasi.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<JabatanJson>> getJabatans(
			@RequestParam("instansi") String instansi,
			@RequestParam("lokasi") String lokasi) {

		List<RefJabatan> jabatans = registrasiService.getJabatan(instansi,
				lokasi);
		List<JabatanJson> newJabatans = new ArrayList<JabatanJson>();
		for (RefJabatan jabatan : jabatans) {
			newJabatans.add(new JabatanJson(jabatan.getKode(), jabatan
					.getNama()));
		}
		Map<String, List<JabatanJson>> lokasiMap = new HashMap<String, List<JabatanJson>>();
		lokasiMap.put("jabatans", newJabatans);
		return lokasiMap;
	}*/
	@RequestMapping(value = "/cb_jabatan_by_instansi_lokasi.do", method = RequestMethod.GET)
	@ResponseBody
	public String getJabatans(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi,
			@RequestParam("lokasi") String lokasi) throws Exception{

		ObjectMapper objectMapper = new ObjectMapper();
		List<RefJabatan> jabatans = registrasiService.getJabatan(instansi,
				lokasi);
		List<JabatanJson> newJabatans = new ArrayList<JabatanJson>();
		for (RefJabatan jabatan : jabatans) {
			newJabatans.add(new JabatanJson(jabatan.getKode(), jabatan
					.getNama()));
		}
		
		Map<String, List<JabatanJson>> jabatanMap = new HashMap<String, List<JabatanJson>>();
		jabatanMap.put("jabatans", newJabatans);
		
		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				jabatanMap));
		//
	}

	/*
	 * @RequestMapping(value = "/cb_pendidikan_by_instansi_lokasi_jabatan.do",
	 * method = RequestMethod.GET)
	 * 
	 * @ResponseBody public Map<String, List<RefPendidikan>> getPendidikans(
	 * 
	 * @RequestParam("instansi") String instansi,
	 * 
	 * @RequestParam("lokasi") String lokasi,
	 * 
	 * @RequestParam("jabatan") String jabatan) {
	 * 
	 * List<RefPendidikan> pendidikans = registrasiService.getPendidikan(
	 * instansi, lokasi, jabatan); Map<String, List<RefPendidikan>>
	 * pendidikanMap = new HashMap<String, List<RefPendidikan>>();
	 * pendidikanMap.put("pendidikans", pendidikans); return pendidikanMap; }
	 */
	/*@RequestMapping(value = "/cb_pendidikan_by_instansi_lokasi_jabatan.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<PendidikanJson>> getPendidikans(
			@RequestParam("instansi") String instansi,
			@RequestParam("lokasi") String lokasi,
			@RequestParam("jabatan") String jabatan) {

		List<RefPendidikan> pendidikans = registrasiService.getPendidikan(
				instansi, lokasi, jabatan);
		List<PendidikanJson> newPendidikans = new ArrayList<PendidikanJson>();
		for (RefPendidikan pendidikan : pendidikans) {
			newPendidikans.add(new PendidikanJson(pendidikan.getKode(),
					pendidikan.getNama(), pendidikan.getTingkat()));
		}
		Map<String, List<PendidikanJson>> pendidikanMap = new HashMap<String, List<PendidikanJson>>();
		pendidikanMap.put("pendidikans", newPendidikans);
		return pendidikanMap;
	}*/
	
	@RequestMapping(value = "/cb_pendidikan_by_instansi_lokasi_jabatan.do", method = RequestMethod.GET)
	@ResponseBody
	public String getPendidikans(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi,
			@RequestParam("lokasi") String lokasi,
			@RequestParam("jabatan") String jabatan) throws Exception{

		ObjectMapper objectMapper = new ObjectMapper();
		List<RefPendidikan> pendidikans = registrasiService.getPendidikan(
				instansi, lokasi, jabatan);
		List<PendidikanJson> newPendidikans = new ArrayList<PendidikanJson>();
		for (RefPendidikan pendidikan : pendidikans) {
			newPendidikans.add(new PendidikanJson(pendidikan.getKode(),
					pendidikan.getNama(), pendidikan.getTingkat()));
		}
		Map<String, List<PendidikanJson>> pendidikanMap = new HashMap<String, List<PendidikanJson>>();
		pendidikanMap.put("pendidikans", newPendidikans);
		
		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				pendidikanMap));
	}
	
	
	
	//after registrasi
	@RequestMapping(value = "/afterRegistrasi.do", method = RequestMethod.GET)
	public String afterRegistrasi(@RequestParam("idRegistrasi") String idRegistrasi, ModelMap model) {
		model.addAttribute("idRegistrasi", idRegistrasi);
		return "afterRegistrasi";
	}

	//updated
	@RequestMapping(value = "/cb_pendidikan_by_instansi_lokasi.do", method = RequestMethod.GET)
	@ResponseBody
	public String getPendidikans(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi,
			@RequestParam("lokasi") String lokasi) throws Exception{

		ObjectMapper objectMapper = new ObjectMapper();
		List<RefPendidikan> pendidikans = registrasiService.getPendidikan(
				instansi, lokasi);
		List<PendidikanJson> newPendidikans = new ArrayList<PendidikanJson>();
		for (RefPendidikan pendidikan : pendidikans) {
			newPendidikans.add(new PendidikanJson(pendidikan.getKode(),
					pendidikan.getNama(), pendidikan.getTingkat()));
		}
		Map<String, List<PendidikanJson>> pendidikanMap = new HashMap<String, List<PendidikanJson>>();
		pendidikanMap.put("pendidikans", newPendidikans);
		
		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				pendidikanMap));
	}
	
	
	@RequestMapping(value = "/cb_jabatan_by_instansi_lokasi_pendidikan.do", method = RequestMethod.GET)
	@ResponseBody
	public String getJabatans(@RequestParam("callback") String callBack,
			@RequestParam("instansi") String instansi,
			@RequestParam("lokasi") String lokasi,
			@RequestParam("pendidikan") String pendidikan) throws Exception{

		ObjectMapper objectMapper = new ObjectMapper();
		List<RefJabatan> jabatans = registrasiService.getJabatan(instansi,
				lokasi,pendidikan);
		List<JabatanJson> newJabatans = new ArrayList<JabatanJson>();
		for (RefJabatan jabatan : jabatans) {
			newJabatans.add(new JabatanJson(jabatan.getKode(), jabatan
					.getNama()));
		}
		
		Map<String, List<JabatanJson>> jabatanMap = new HashMap<String, List<JabatanJson>>();
		jabatanMap.put("jabatans", newJabatans);
		
		return objectMapper.writeValueAsString(new JSONPObject(callBack,
				jabatanMap));
	}

}
