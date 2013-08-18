package org.sscn.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.RefInstansi;

@Repository("DtPendaftaranDao")
public class DtPendaftaranDaoImpl extends CoreDaoImpl<DtPendaftaran> implements DtPendaftaranDao{
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtPendaftaranDaoImpl(SessionFactory sessionFactory) {
		super(DtPendaftaran.class, sessionFactory);
	}
	
	@Override
	public List<DtPendaftaran> findByInstansi(RefInstansi instansi,int... idxAndCount){
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery()); 
				
				//new StringBuilder("SELECT model FROM DtPendaftaran model ");
		
		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.formasi");
//		final String innerJoinFetchPhrase = "LEFT JOIN FETCH model.mFormasi formasi ";
		StringBuilder wherePhrase = new StringBuilder(" WHERE (model.formasi.refInstansi.kode = :refInstansiId) ");
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase);
		// find query		
		System.out.println(sbFind);
		Query query = createQuery(sbFind);
		
		query.setParameter("refInstansiId", instansi.getKode());
		return doQuery(query, idxAndCount);
	}
}
