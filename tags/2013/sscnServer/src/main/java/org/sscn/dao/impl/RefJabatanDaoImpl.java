package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.RefJabatanDao;
import org.sscn.persistence.entities.RefJabatan;

@Repository("RefJabatanDao")
public class RefJabatanDaoImpl extends CoreDaoImpl<RefJabatan> implements
		RefJabatanDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public RefJabatanDaoImpl(SessionFactory sessionFactory) {
		super(RefJabatan.class, sessionFactory);
	}
}
