package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.RefLokasiTestDao;
import org.sscn.persistence.entities.RefLokasiTest;

@Repository("RefLokasiTestDao")
public class RefLokasiTestDaoImpl extends CoreDaoImpl<RefLokasiTest> implements
		RefLokasiTestDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public RefLokasiTestDaoImpl(SessionFactory sessionFactory) {
		super(RefLokasiTest.class, sessionFactory);
	}
}
