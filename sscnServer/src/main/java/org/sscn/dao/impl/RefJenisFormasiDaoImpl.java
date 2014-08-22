package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.RefJenisFormasiDao;
import org.sscn.persistence.entities.RefJenisFormasi;

@Repository("RefJenisFormasiDao")
public class RefJenisFormasiDaoImpl extends CoreDaoImpl<RefJenisFormasi> implements
		RefJenisFormasiDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public RefJenisFormasiDaoImpl(SessionFactory sessionFactory) {
		super(RefJenisFormasi.class, sessionFactory);
	}
}
