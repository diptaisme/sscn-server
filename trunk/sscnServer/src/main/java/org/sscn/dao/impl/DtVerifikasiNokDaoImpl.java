package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.DtVerifikasiNokDao;
import org.sscn.persistence.entities.DtVerifikasiNok;

@Repository("DtVerifikasiNokDao")
public class DtVerifikasiNokDaoImpl extends CoreDaoImpl<DtVerifikasiNok> implements DtVerifikasiNokDao{
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtVerifikasiNokDaoImpl(SessionFactory sessionFactory) {
		super(DtVerifikasiNok.class, sessionFactory);
	}
}
