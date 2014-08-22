package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.DtFormasiDao;
import org.sscn.dao.DtVerifikasiUlangDao;
import org.sscn.persistence.entities.DtFormasi;
import org.sscn.persistence.entities.DtVerifikasiUlang;

@Repository("DtVerifikasiUlangDao")
public class DtVerifikasiUlangDaoImpl extends CoreDaoImpl<DtVerifikasiUlang> implements DtVerifikasiUlangDao{
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtVerifikasiUlangDaoImpl(SessionFactory sessionFactory) {
		super(DtVerifikasiUlang.class, sessionFactory);
	}
}
