package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.DtFormasiDao;
import org.sscn.persistence.entities.DtFormasi;

@Repository("DtFormasiDao")
public class DtFormasiDaoImpl extends CoreDaoImpl<DtFormasi> implements DtFormasiDao{
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtFormasiDaoImpl(SessionFactory sessionFactory) {
		super(DtFormasi.class, sessionFactory);
	}
}
