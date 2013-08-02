package org.sscn.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.services.RegistrasiService;

@Controller
@RequestMapping("/registrasi.do")
public class RegistrasiController {
	@Inject
	private RegistrasiService registrasiService;

	@RequestMapping(method = RequestMethod.POST)
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
		pendaftaran.setInstansi("inst");
		pendaftaran.setJabatan("Jab");
		pendaftaran.setJnsKelamin("P");
		pendaftaran.setKeterangan("sdsadsa keterangan");
		pendaftaran.setLembaga("lem");
		pendaftaran.setLokasi("lok");
		pendaftaran.setLokasiTest("lokT");
		
		
		int minute=Calendar.getInstance().getTime().getMinutes();
		int day=Calendar.getInstance().getTime().getDay();
		int second =Calendar.getInstance().getTime().getSeconds();
		int x=noKtp.length()>7?2:3;
		int y=noKtp.length()%2>7?0:1;
		String noPeserta=""+x+minute+"0"+day+y+second;  //random example
		String noRegister = ""+y+day+minute+x+second; //random example
		if(noPeserta.length()>10){
			noPeserta=noPeserta.substring(0, 10);
		}
		if(noRegister.length()>10){
			noRegister=noRegister.substring(0, 10);
		}
		pendaftaran.setNoPeserta(noPeserta);
		pendaftaran.setNoRegister(noRegister); //example
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
	private String generateNoRegistrasi(){
		return "";		
	}
}
