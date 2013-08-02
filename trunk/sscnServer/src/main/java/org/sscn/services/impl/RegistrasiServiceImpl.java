package org.sscn.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.services.RegistrasiService;

@Service("RegistrasiService")
public class RegistrasiServiceImpl implements RegistrasiService {
	@Inject
	private DtPendaftaranDao dtPendaftaranDao;

	@Transactional(readOnly = false)
	public DtPendaftaran insertNewRegistrasi(DtPendaftaran dtPendaftaran) {
		dtPendaftaranDao.insert(dtPendaftaran);
		return dtPendaftaran;
	}
}
