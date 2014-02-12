package org.sscn.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.DtHasilDao;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.dao.DtPesertaDao;
import org.sscn.persistence.entities.DtHasil;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.DtPeserta;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.persistence.entities.view.DataPendaftaran;
import org.sscn.persistence.entities.view.RekapanPendaftaran;
import org.sscn.persistence.entities.view.StatInstansi;
import org.sscn.persistence.entities.view.StatInstansiJabatan;

@Repository("DtPesertaDao")
public class DtPesertaDaoImpl extends CoreDaoImpl<DtPeserta> implements
		DtPesertaDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtPesertaDaoImpl(SessionFactory sessionFactory) {
		super(DtPeserta.class, sessionFactory);
	}
}
