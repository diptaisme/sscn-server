package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.RefLokasiDao;
import org.sscn.persistence.entities.RefLokasi;

@Repository("RefLokasiDao")
public class RefLokasiDaoImpl extends CoreDaoImpl<RefLokasi> implements
		RefLokasiDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public RefLokasiDaoImpl(SessionFactory sessionFactory) {
		super(RefLokasi.class, sessionFactory);
	}
}
