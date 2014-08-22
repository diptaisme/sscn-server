package org.sscn.dao;

import java.util.List;
import java.util.Map;

import org.sscn.core.persistence.dao.CoreDao;
import org.sscn.persistence.entities.RefPendidikan;

public interface RefPendidikanDao extends CoreDao<RefPendidikan> {
	
	List<RefPendidikan> findPendidikanByLikeNameAndTkPddkn(Map<String, Object> map,
			int... idxAndCount);
}
