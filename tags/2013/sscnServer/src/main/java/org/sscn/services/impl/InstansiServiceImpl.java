package org.sscn.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.RefInstansiDao;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.services.InstansiService;

@Service("InstansiService")
public class InstansiServiceImpl implements InstansiService {

	@Inject
	private RefInstansiDao refInstansiDao;
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefInstansi> findAllInstansi(int... idx) {
		return refInstansiDao.findAll(idx);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefInstansi> findInstansiByLikeName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nama", name);
		List<RefInstansi> instansis = refInstansiDao.findPrefixLikeMapOfProperties(map, null, null, null);
//		List<RefInstansi> instansis = refInstansiDao.findLikeMapOfProperties(
//				map, null);
		return instansis;
	}
	
}
