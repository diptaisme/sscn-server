package org.sscn.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.core.persistence.tools.QueryOrder;
import org.sscn.dao.DtPersyaratanDao;
import org.sscn.persistence.entities.DtPersyaratan;
import org.sscn.persistence.entities.DtUser;
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
	
	@Override
	@Transactional(readOnly = false)
	public boolean delete(DtPersyaratan syarat) {
		try {
			persyaratanDao.remove(syarat);			
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public List<DtPersyaratan> findByProperty(String name, Object value,List<QueryOrder> orders, int...idx){
		return persyaratanDao.findByProperty(name, value, orders, idx);
	}
}
