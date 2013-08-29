package org.sscn.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.dao.MFormasiDao;
import org.sscn.dao.RefInstansiDao;
import org.sscn.dao.RefLokasiDao;
import org.sscn.dao.RefPendidikanDao;
import org.sscn.persistence.entities.DtFormasi;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.MFormasi;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.persistence.entities.RefJabatan;
import org.sscn.persistence.entities.RefLokasi;
import org.sscn.persistence.entities.RefPendidikan;
import org.sscn.services.RegistrasiService;

@Service("RegistrasiService")
public class RegistrasiServiceImpl implements RegistrasiService {
	@Inject
	private DtPendaftaranDao dtPendaftaranDao;
	@Inject
	private RefInstansiDao refInstansiDao;
	@Inject
	private RefLokasiDao refLokasiDao;
	@Inject
	private MFormasiDao mFormasiDao;

	@Inject
	private RefPendidikanDao refPendidikanDao;

	@Transactional(readOnly = false)
	public DtPendaftaran insertNewRegistrasi(DtPendaftaran dtPendaftaran) {
		dtPendaftaranDao.insert(dtPendaftaran);
		return dtPendaftaran;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefInstansi> getInstansi(int maxRows, String startWith) {
		return refInstansiDao.findLikeProperty("nama", startWith, new int[] {
				0, maxRows });
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefLokasi> getLokasi(String instansi) {
		List<MFormasi> listFormasi = mFormasiDao.findByProperty(
				"refInstansi.kode", instansi, null);
		List<RefLokasi> listLokasi = new ArrayList<RefLokasi>();

		for (MFormasi formasi : listFormasi) {
			listLokasi.add(formasi.getRefLokasi());
		}
		return listLokasi;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefJabatan> getJabatan(String instansi, String lokasi) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("refInstansi.kode", instansi);
		map.put("refLokasi.kode", lokasi);

		List<MFormasi> listFormasi = mFormasiDao.findByMapOfProperties(map,
				null, null);

		List<RefJabatan> listJabatan = new ArrayList<RefJabatan>();

		for (MFormasi formasi : listFormasi) {
			listJabatan.add(formasi.getRefJabatan());
		}
		return listJabatan;

	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefPendidikan> getPendidikan(String instansi, String lokasi,
			String jabatan) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("refInstansi.kode", instansi);
		map.put("refLokasi.kode", lokasi);
		map.put("refJabatan.kode", jabatan);

		List<MFormasi> listFormasi = mFormasiDao.findByMapOfProperties(map,
				null, null);

		List<RefPendidikan> listPendidikan = new ArrayList<RefPendidikan>();

		MFormasi formasi = listFormasi.get(0);

		Iterator<DtFormasi> iterator = formasi.getDtFormasis().iterator();
		while (iterator.hasNext()) {
			DtFormasi dtFormasi = iterator.next();
			listPendidikan.add(dtFormasi.getPendidikan());
		}

		return listPendidikan;

	}

	@Override
	@Transactional(readOnly = false)
	public DtPendaftaran insertPendaftaran(HttpServletRequest request) {
		try {

			String noNik = request.getParameter("no_nik");

			String nama = request.getParameter("nama");
			String tempatLahir = request.getParameter("tempat_lahir");
			String strTglLahir = request.getParameter("datepickerTglLahir");
			Date tglLahir = new SimpleDateFormat("dd-MM-yyyy")
					.parse(strTglLahir);

			String alamat = request.getParameter("alamat");
			String kota = request.getParameter("kota");
			String propinsi = request.getParameter("propinsi");
			String kodePos = request.getParameter("kode_pos");
			String telpon = request.getParameter("telpon");
			String email = request.getParameter("email");

			String instansi = request.getParameter("instansiValue");
			String jabatan = request.getParameter("jabatan");
			String lokasiKerja = request.getParameter("lokasi_kerja");
			String pendidikan = request.getParameter("pendidikan"); //kode yg disimpan
			//String pendidikan = refPendidikanDao
				//	.findByProperty("kode", request.getParameter("pendidikan"),
					//		null).get(0).getNama();

			MFormasi mFormasi = null; // formasi nanti, sudah
			HashMap<String, String> propertiesMap = new HashMap<String, String>();
			propertiesMap.put("refInstansi.kode", instansi);
			propertiesMap.put("refLokasi.kode", lokasiKerja);
			propertiesMap.put("refJabatan.kode", jabatan);
			List<MFormasi> listFormasi = mFormasiDao.findByMapOfProperties(
					propertiesMap, null, null);
			if (listFormasi == null || listFormasi.size() == 0) {
				return null;
			} else {
				mFormasi = listFormasi.get(0);
			}

			String noIjazah = request.getParameter("no_ijazah");
			String jnsKelamin = request.getParameter("jenis_kelamin");
			String status = ""; // status
			String regStatus = ""; // regStatus			

			String lembaga = request.getParameter("universitas"); // lembaga = universitas??
			String akreditasi = request.getParameter("akreditasi");
			String nilaiIPK = request.getParameter("nilai_ipk");
			// memang tidak diisi
			Date tglTest = new Date();
			String lokasiTest = "";
			Date tglCreated = new Date();
			Date tglUpdated = new Date();
			String userValidate = "";
			Date tglValidate = new Date();
			String keterangan = "";
			
			// noRegister = ""; // generate no register
			// noPeserta = ""; // kosong
			
			String noPeserta = "";

			String noRegister= generateNoRegistrasi();
			if (noPeserta.length() > 10) {
				noPeserta = noPeserta.substring(0, 10);
			}
			if (noRegister.length() > 10) {
				noRegister = noRegister.substring(0, 10);
			}

			DtPendaftaran pendaftaran = new DtPendaftaran(mFormasi, noNik,
					noRegister, nama, tempatLahir, tglLahir, jnsKelamin,
					alamat, kodePos, propinsi, kota, telpon, email, pendidikan,
					lembaga, noIjazah, status, regStatus, noPeserta, tglTest,
					lokasiTest, tglCreated, tglUpdated, userValidate,
					tglValidate, keterangan, akreditasi, nilaiIPK);
			dtPendaftaranDao.insert(pendaftaran);
			return pendaftaran;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public DtPendaftaran getPendaftaranByNoRegistrasi(String noRegister){		
		List<DtPendaftaran> listPendaftarans = dtPendaftaranDao.findByProperty("noRegister", noRegister, null);
		if(listPendaftarans.size() > 0){
			return listPendaftarans.get(0);
		}else{
			return null;
		}
	}
	
	// tanpa tingkat pendidikan dulu
	// private String generateNoRegistrasi(String kodeInstansi, String
	// tingkatPendidikan, String kodeJabatan){
	private String generateNoRegistrasi() {
		Random r = new Random(System.nanoTime());
		long resut = 1000000000 + r.nextInt(2000000000);
		if (resut < 0) {
			resut = resut * -1;
		}
		Long finalResult = Long.valueOf(resut);
		return finalResult.toString();
	}
}
