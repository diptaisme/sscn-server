package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.persistence.entities.DtPendaftaran;

@Repository("DtPendaftaranDao")
public class DtPendaftaranDaoImpl extends CoreDaoImpl<DtPendaftaran> implements DtPendaftaranDao{
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtPendaftaranDaoImpl(SessionFactory sessionFactory) {
		super(DtPendaftaran.class, sessionFactory);
	}
}
