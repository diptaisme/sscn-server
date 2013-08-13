package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.DtPengumumanDao;
import org.sscn.persistence.entities.DtPengumuman;

@Repository("DtPengumumanDao")
public class DtPengumumanDaoImpl extends CoreDaoImpl<DtPengumuman> implements
		DtPengumumanDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtPengumumanDaoImpl(SessionFactory sessionFactory) {
		super(DtPengumuman.class, sessionFactory);
	}
}
