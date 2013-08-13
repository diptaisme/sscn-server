package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.RefInstansiDao;
import org.sscn.persistence.entities.RefInstansi;

@Repository("RefInstansiDao")
public class RefInstansiDaoImpl extends CoreDaoImpl<RefInstansi> implements RefInstansiDao{
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public RefInstansiDaoImpl(SessionFactory sessionFactory) {
		super(RefInstansi.class, sessionFactory);
	}
}
