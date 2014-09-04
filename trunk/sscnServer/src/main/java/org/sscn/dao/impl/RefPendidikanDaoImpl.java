package org.sscn.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.RefPendidikanDao;
import org.sscn.persistence.entities.RefPendidikan;
import org.sscn.persistence.entities.view.DataPendaftaran;

@Repository("RefPendidikanDao")
public class RefPendidikanDaoImpl extends CoreDaoImpl<RefPendidikan> implements
		RefPendidikanDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public RefPendidikanDaoImpl(SessionFactory sessionFactory) {
		super(RefPendidikan.class, sessionFactory);
	}
	
	@Override
	public List<RefPendidikan> findPendidikanByLikeNameAndTkPddkn(Map<String, Object> map,
			int... idxAndCount) {
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());

		//final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.formasi");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE model.tingkat = :tingkat and model.nama LIKE :nama order by model.nama");
		sbFind.append(wherePhrase);
		
		Query query = createQuery(sbFind);
		
		query.setParameter("tingkat", map.get("tingkat"));
		query.setParameter("nama", "%"+map.get("nama")+"%");
		return doQuery(query, idxAndCount);
	}
	
	@Override
	public List<RefPendidikan> findPendidikanOfPendaftar(String searchTerm) {
		List<RefPendidikan> result = new ArrayList<RefPendidikan>();
		final String sql = "select ref_pendidikan.kode,ref_pendidikan.nama, ref_pendidikan.tingkat from ref_pendidikan inner join "+
						   " (select count(*), pendidikan from dt_pendaftaran group by pendidikan) a on a.pendidikan = ref_pendidikan.KODE where nama like '%"+searchTerm+"%'";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					RefPendidikan dataPendidikan = new RefPendidikan();
					int index = 0;
					dataPendidikan.setKode(String.valueOf(obj[index++]));
					dataPendidikan.setNama(String.valueOf(obj[index++]));
					dataPendidikan.setTingkat(String.valueOf(obj[index++]));
					
					result.add(dataPendidikan);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

}
