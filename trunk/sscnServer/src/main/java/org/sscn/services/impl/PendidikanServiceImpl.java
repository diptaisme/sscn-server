package org.sscn.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.RefPendidikanDao;
import org.sscn.persistence.entities.RefPendidikan;
import org.sscn.services.PendidikanService;

@Service("PendidikanService")
public class PendidikanServiceImpl implements PendidikanService {

	@Inject
	private RefPendidikanDao refPendidikanDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefPendidikan> findAllPendidikan(int... idx) {
		return refPendidikanDao.findAll(idx);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefPendidikan> findPendidikanByLikeName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nama", name);
		List<RefPendidikan> pendidikans = refPendidikanDao
				.findPrefixLikeMapOfProperties(map, null, null, null);
		return pendidikans;
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefPendidikan> findPendidikanByLikeNameAndTkPddkn(String name, String tingkat) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nama", name);
		map.put("tingkat", tingkat);
		int indexAndCount[] = new int[2]; 
		indexAndCount[0] = 0;
		indexAndCount[1] = 100;
		List<RefPendidikan> pendidikans = refPendidikanDao
				.findPendidikanByLikeNameAndTkPddkn(map, indexAndCount);
		return pendidikans;
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefPendidikan> findPendidikanOfPendaftar(String searchTerm) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<RefPendidikan> pendidikans = refPendidikanDao
				.findPendidikanOfPendaftar(searchTerm);
		return pendidikans;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public RefPendidikan findById(String id) {
		return refPendidikanDao.findById(id);
	}
}
