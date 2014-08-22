package org.sscn.dao;

import java.util.List;

import org.sscn.core.persistence.dao.CoreDao;
import org.sscn.persistence.entities.DtUser;

public interface DtUserDao extends CoreDao<DtUser> {
	List<DtUser> findByInstansi(String instansi, String order, int... idxAndCount);
	Integer countByInstansi(String refInstansi);
	List<DtUser> findByInstansiAndNip(String instansi, String nip, int... idxAndCount);
	Integer countByInstansiAndNip(String refInstansi, String nip);

}
