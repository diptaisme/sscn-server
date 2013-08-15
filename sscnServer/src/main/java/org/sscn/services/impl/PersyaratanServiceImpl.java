package org.sscn.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.DtPersyaratanDao;
import org.sscn.persistence.entities.DtPersyaratan;
import org.sscn.services.PersyaratanService;

/**
 * @author efraim
 * 
 */
@Service(value = "persyaratanServiceImpl")
public class PersyaratanServiceImpl implements PersyaratanService {

	@Inject
	private DtPersyaratanDao persyaratanDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sscn.services.PersyaratanService#simpanPersyaratan(org.sscn.persistence
	 * .entities.DtPersyaratan)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public DtPersyaratan simpanPersyaratan(DtPersyaratan persyaratan) {
		DtPersyaratan newInstance = null;
		if (persyaratan != null) {
			if (persyaratan.getId() == null) {
				newInstance = persyaratanDao.insert(persyaratan);
			} else {
				newInstance = persyaratanDao.update(persyaratan);
			}
		}
		return newInstance;
	}

}
