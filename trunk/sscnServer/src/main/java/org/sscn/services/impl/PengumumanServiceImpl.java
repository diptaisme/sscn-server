package org.sscn.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.DtPengumumanDao;
import org.sscn.dao.RefInstansiDao;
import org.sscn.persistence.entities.DtPengumuman;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.services.PengumumanService;

@Service("PengumumanService")
public class PengumumanServiceImpl implements PengumumanService {
	@Inject
	private DtPengumumanDao dtPengumumanDao;
	@Inject
	private RefInstansiDao refInstansiDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<DtPengumuman> getPengumuman(int... idx) {
		return dtPengumumanDao.findAll(idx);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefInstansi> getInstansi(int... idx) {
		return refInstansiDao.findAll(idx);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePengumuman(DtPengumuman dtPengumuman) {
		dtPengumumanDao.updatePengumuman(dtPengumuman);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public void insertPengumuman(DtPengumuman dtPengumuman) {
		dtPengumumanDao.insert(dtPengumuman);
	}
}
