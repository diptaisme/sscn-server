package org.sscn.dao;

import java.util.List;

import org.sscn.core.persistence.dao.CoreDao;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.DtPersyaratan;
import org.sscn.persistence.entities.RefInstansi;

public interface DtPendaftaranDao extends CoreDao<DtPendaftaran>{
	
	List<DtPendaftaran> findByInstansi(RefInstansi instansi, final int... idxAndCount);
}
