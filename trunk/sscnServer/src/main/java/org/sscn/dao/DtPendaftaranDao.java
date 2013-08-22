package org.sscn.dao;

import java.util.List;
import java.util.Map;

import org.sscn.core.persistence.dao.CoreDao;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.RefInstansi;

public interface DtPendaftaranDao extends CoreDao<DtPendaftaran> {

	String getnoUrutPendaftaran(String sebelasDigitPertama);

	List<DtPendaftaran> findByInstansi(RefInstansi instansi,
			final int... idxAndCount);
	
	List<DtPendaftaran> findByInstansiAndMap(RefInstansi instansi, Map<String, Object> map,
			final int... idxAndCount);

	Integer countByInstansi(RefInstansi refInstansi);
	
	Integer countByInstansiAndMap(RefInstansi refInstansi, Map<String, Object> map);

	
}
