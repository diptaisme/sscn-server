package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.DtPersyaratanDao;
import org.sscn.persistence.entities.DtPersyaratan;

@Repository("dtPersyaratanDaoImpl")
public class DtPersyaratanDaoImpl extends CoreDaoImpl<DtPersyaratan> implements
        DtPersyaratanDao {

	/**
	 * @param sessionFactory
	 */
	@Autowired
	public DtPersyaratanDaoImpl(SessionFactory sessionFactory) {
		super(DtPersyaratan.class, sessionFactory);
	}
}
