package org.sscn.dao;

import java.util.List;

import org.sscn.core.persistence.dao.CoreDao;
import org.sscn.core.persistence.tools.PropCriteriaAndValue;
import org.sscn.core.persistence.tools.QueryOrder;
import org.sscn.persistence.entities.MFormasi;

public interface MFormasiDao extends CoreDao<MFormasi> {

	List<MFormasi> findFormasiUmumByLokasiAndNamaJabatan(String lokasiId, String namaJabatan, List<QueryOrder> orders, int... idxAndCount);
	Integer countFormasiUmumByLokasiAndNamaJabatan(String lokasiId, String namaJabatan);
	List<MFormasi> findFormasiUmum(String instansiId, List<QueryOrder> orders, int... idxAndCount);
	Integer countFormasiUmum(String instansiId);
	
	List<MFormasi> findFormasiKhusus(String instansiId, List<QueryOrder> orders, int... idxAndCount);
	Integer countFormasiKhusus(String instansiId);
	
	List<MFormasi> findFormasiKhususUsingFilter(List<PropCriteriaAndValue> filterList, List<QueryOrder> orders, int... idxAndCount);
	Integer countFormasiKhususUsingFilter(List<PropCriteriaAndValue> filterList);

	List<MFormasi> findByInstansiLokasiPendidikan(String instansi,
			String lokasi, String pendidikan);

}
