package org.sscn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.DtUserDao;
import org.sscn.persistence.entities.DtUser;

@Repository("DtUserDao")
public class DtUserDaoImpl extends CoreDaoImpl<DtUser> implements DtUserDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtUserDaoImpl(SessionFactory sessionFactory) {
		super(DtUser.class, sessionFactory);
	}
	
	@Override
	public List<DtUser> findByInstansi(String kdInstansi, int... idxAndCount) {
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());

		
		StringBuilder wherePhrase = new StringBuilder(
		        " WHERE (model.refInstansi.kode = :refInstansiId) and model.kewenangan = '3' ");
		sbFind.append(wherePhrase);

		Query query = createQuery(sbFind);

		query.setParameter("refInstansiId", kdInstansi);
		return doQuery(query, idxAndCount);
	}
	
	@Override
	public Integer countByInstansi(String kdInstansi) {
		StringBuilder sbFind = new StringBuilder(
		        "SELECT COUNT(model.id) FROM DtUser model ");
		StringBuilder wherePhrase = new StringBuilder(
		        " WHERE model.refInstansi.kode = :refInstansiId and model.kewenangan = '3' ");
		sbFind.append(wherePhrase);
		Query query = createQuery(sbFind);

		query.setParameter("refInstansiId", kdInstansi);
		return Integer.valueOf(query.uniqueResult().toString());
	}


}