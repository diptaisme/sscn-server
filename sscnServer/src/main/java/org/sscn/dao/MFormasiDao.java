package org.sscn.dao;

import java.util.List;

import org.sscn.core.persistence.dao.CoreDao;
import org.sscn.persistence.entities.MFormasi;

public interface MFormasiDao extends CoreDao<MFormasi> {
	List<MFormasi> findByInstansiLokasiPendidikan(String instansi,
			String lokasi, String pendidikan);
}
