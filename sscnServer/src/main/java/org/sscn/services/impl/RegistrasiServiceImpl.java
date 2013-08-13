package org.sscn.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.dao.MFormasiDao;
import org.sscn.dao.RefInstansiDao;
import org.sscn.dao.RefLokasiDao;
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
		map.put("refInstansi.kode",instansi);
		map.put("refLokasi.kode",lokasi);
		
		List<MFormasi> listFormasi = mFormasiDao.findByMapOfProperties(map, null, null);

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
}
