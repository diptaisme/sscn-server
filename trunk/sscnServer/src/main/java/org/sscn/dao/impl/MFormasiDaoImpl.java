package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.MFormasiDao;
import org.sscn.persistence.entities.MFormasi;

@Repository("MFormasiDao")
public class MFormasiDaoImpl extends CoreDaoImpl<MFormasi> implements
		MFormasiDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public MFormasiDaoImpl(SessionFactory sessionFactory) {
		super(MFormasi.class, sessionFactory);
	}
}
