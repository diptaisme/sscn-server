package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.RefLokasiDao;
import org.sscn.dao.RefPendidikanDao;
import org.sscn.persistence.entities.RefLokasi;
import org.sscn.persistence.entities.RefPendidikan;

@Repository("RefPendidikanDao")
public class RefPendidikanDaoImpl extends CoreDaoImpl<RefPendidikan> implements
		RefPendidikanDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public RefPendidikanDaoImpl(SessionFactory sessionFactory) {
		super(RefPendidikan.class, sessionFactory);
	}
}
