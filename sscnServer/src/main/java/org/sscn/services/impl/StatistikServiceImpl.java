package org.sscn.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.persistence.entities.view.StatInstansi;
import org.sscn.persistence.entities.view.StatInstansiJabatan;
import org.sscn.persistence.entities.view.StatInstansiJabatan3Pilihan;
import org.sscn.services.StatistikService;

@Service("StatistikService")
public class StatistikServiceImpl implements StatistikService {
	@Inject
	private DtPendaftaranDao dtPendaftaranDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<StatInstansi> getStatistikPendaftaranInstansi() {
		return dtPendaftaranDao.getStatistikPendaftaranInstansi();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<StatInstansiJabatan> getStatistikJabatanPendaftaranInstansi(
			String kodeInstansi) {
		return dtPendaftaranDao
				.getStatistikJabatanPendaftaranInstansi(kodeInstansi);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<StatInstansi> getStatistikPendaftaranInstansi(
			String kodeInstansi) {
		return dtPendaftaranDao.getStatistikPendaftaranInstansi(kodeInstansi);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<StatInstansiJabatan3Pilihan> getStatistikJabatanPendaftaranInstansi3Pilihan(
			String kodeInstansi) {
		return dtPendaftaranDao
				.getStatistikJabatanPendaftaranInstansi3Pilihan(kodeInstansi);
	}

}
