package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.PeriodeDaftarDao;
import org.sscn.dao.RefInstansiDao;
import org.sscn.persistence.entities.PeriodeDaftar;
import org.sscn.persistence.entities.RefInstansi;

@Repository("PeriodeDaftarDao")
public class PeriodeDaftarDaoImpl extends CoreDaoImpl<PeriodeDaftar> implements PeriodeDaftarDao{
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public PeriodeDaftarDaoImpl(SessionFactory sessionFactory) {
		super(PeriodeDaftar.class, sessionFactory);
	}
}
