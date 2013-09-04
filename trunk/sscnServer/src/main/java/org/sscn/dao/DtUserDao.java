package org.sscn.dao;

import java.util.List;

import org.sscn.core.persistence.dao.CoreDao;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.RefInstansi;

public interface DtUserDao extends CoreDao<DtUser> {
	List<DtUser> findByInstansi(String instansi, int... idxAndCount);
	Integer countByInstansi(String refInstansi);
}
