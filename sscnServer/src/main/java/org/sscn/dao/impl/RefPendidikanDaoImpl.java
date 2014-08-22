package org.sscn.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.RefPendidikanDao;
import org.sscn.persistence.entities.RefPendidikan;

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
}
