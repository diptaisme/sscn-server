package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.DtHasilDao;
import org.sscn.persistence.entities.DtHasil;

@Repository("DtHasilDao")
public class DtHasilDaoImpl extends CoreDaoImpl<DtHasil> implements
		DtHasilDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtHasilDaoImpl(SessionFactory sessionFactory) {
		super(DtHasil.class, sessionFactory);
	}
}
