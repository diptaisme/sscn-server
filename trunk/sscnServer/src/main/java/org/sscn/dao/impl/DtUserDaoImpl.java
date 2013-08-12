package org.sscn.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.DtUserDao;
import org.sscn.persistence.entities.DtUser;

@Repository("DtUserDao")
public class DtUserDaoImpl extends CoreDaoImpl<DtUser> implements DtUserDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtUserDaoImpl(SessionFactory sessionFactory) {
		super(DtUser.class, sessionFactory);
	}
}
