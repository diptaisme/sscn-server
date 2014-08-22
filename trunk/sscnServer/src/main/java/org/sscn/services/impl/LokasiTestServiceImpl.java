package org.sscn.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.RefInstansiDao;
import org.sscn.dao.RefLokasiDao;
import org.sscn.dao.RefLokasiTestDao;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.persistence.entities.RefLokasi;
import org.sscn.persistence.entities.RefLokasiTest;
import org.sscn.services.LokasiService;
import org.sscn.services.LokasiTestService;

@Service("LokasiTestService")
public class LokasiTestServiceImpl implements LokasiTestService{

	@Inject
	private RefLokasiTestDao refLokasiTestDao;

	@Inject
	private RefInstansiDao refInstansiDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefLokasiTest> findAllLokasi(int... idx) {
		return refLokasiTestDao.findAll(idx);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefLokasiTest> findAllLokasiByInstansi(String kodeInstansi,int... idx) {
		return refLokasiTestDao.findByProperty("refInstansi.kode", kodeInstansi, null, idx);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefLokasiTest> findLokasiByLikeName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nama", name);
		List<RefLokasiTest> lokasis = refLokasiTestDao.findLikeMapOfProperties(map,
				null);
		return lokasis;
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefLokasiTest> findLokasiByLikeNameInstansi(String name, String instansiKd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nama", name);
		RefInstansi pinstansi = refInstansiDao.findById(instansiKd);
		map.put("refInstansi", pinstansi);
		List<RefLokasiTest> lokasis = refLokasiTestDao.findPrefixLikeMapOfProperties(map,
				null,null,null);
		return lokasis;
	}

	@Override
	@Transactional(readOnly = false)
	public RefLokasiTest save(String kode, String name, String instansiKd) {
		RefLokasiTest lokasi = null;
		try {
			RefInstansi instansi = refInstansiDao.findById(instansiKd);
			lokasi = new RefLokasiTest();
			lokasi.setKode(instansiKd+kode);
			lokasi.setNama(name);
			lokasi.setStatus("1");			
			lokasi.setRefInstansi(instansi);
			lokasi = refLokasiTestDao.insert(lokasi);
		} catch (Exception e) {
			e.printStackTrace();
			lokasi = null;
		}
		return lokasi;
	}

	@Override
	@Transactional(readOnly = false)
	public RefLokasiTest update(String kode, String name) {
		RefLokasiTest lokasi = null;
		try {
			lokasi = refLokasiTestDao.findByProperty("kode", kode, null).get(0);
			// lokasi.setKode(newKode);
			lokasi.setNama(name);
			// RefInstansi instansi = refInstansiDao.findById(instansiKd);
			// lokasi.setRefInstansi(instansi);
			lokasi = refLokasiTestDao.update(lokasi);
		} catch (Exception e) {
			e.printStackTrace();
			lokasi = null;
		}
		return lokasi;
	}

	@Override
	@Transactional(readOnly = false)
	public RefLokasiTest delete(String kode) {
		RefLokasiTest lokasi = null;
		try {
			lokasi = refLokasiTestDao.findByProperty("kode", kode, null).get(0);
			if (!refLokasiTestDao.remove(lokasi)) {
				lokasi = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			lokasi = null;
		}
		return lokasi;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public RefLokasiTest findLokasiById(String kode) {
		RefLokasiTest refLokasi = null;
		try {
			refLokasi = refLokasiTestDao.findById(kode);
		} catch (Exception ex) {
			ex.printStackTrace();
			refLokasi = null;
		}
		return refLokasi;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Integer countFindAllLokasiByInstansi(String kodeInstansi) {
		// TODO Auto-generated method stub
		return refLokasiTestDao.countByProperty("refInstansi.kode", kodeInstansi);
	}

}
