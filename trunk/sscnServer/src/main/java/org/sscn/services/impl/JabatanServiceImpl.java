package org.sscn.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.RefJabatanDao;
import org.sscn.persistence.entities.RefJabatan;
import org.sscn.services.JabatanService;

@Service("JabatanService")
public class JabatanServiceImpl implements JabatanService {

	@Inject
	private RefJabatanDao refJabatanDao;
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefJabatan> findAllJabatan(int... idx) {
		return refJabatanDao.findAll(idx);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefJabatan> findJabatanByLikeName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nama", name);
		List<RefJabatan> jabatans = refJabatanDao.findLikeMapOfProperties(
				map, null);
		return jabatans;
	}
}
