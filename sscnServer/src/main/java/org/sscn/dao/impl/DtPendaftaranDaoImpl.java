package org.sscn.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.RefInstansi;

@Repository("DtPendaftaranDao")
public class DtPendaftaranDaoImpl extends CoreDaoImpl<DtPendaftaran> implements
        DtPendaftaranDao {
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

	@Override
	public List<DtPendaftaran> findByInstansi(RefInstansi instansi, int... idxAndCount) {
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());

		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.formasi");
		StringBuilder wherePhrase = new StringBuilder(
		        " WHERE (model.formasi.refInstansi.kode = :refInstansiId) ");
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase);

		Query query = createQuery(sbFind);

		query.setParameter("refInstansiId", instansi.getKode());
		return doQuery(query, idxAndCount);
	}

	@Override
	public String getnoUrutPendaftaran(String sebelasDigitPertama) {
		StringBuilder sqlText = new StringBuilder(
		        "select  max(convert(substr(NO_PESERTA,14),unsigned integer)) from DT_PENDAFTARAN where NO_PESERTA LIKE '"
		                + sebelasDigitPertama + "%'");

		SQLQuery query = createSqlQuery(sqlText);
		Object myResult = query.uniqueResult();

		if (myResult == null) {
			return "";
		} else {
			String result = String.valueOf(myResult);
			return result;
		}
	}

	@Override
	public Integer countByInstansi(RefInstansi refInstansi) {
		StringBuilder sbFind = new StringBuilder(
		        "SELECT COUNT(model.id) FROM DtPendaftaran model ");
		StringBuilder wherePhrase = new StringBuilder(
		        " WHERE model.formasi.refInstansi.kode = :refInstansiId ");
		sbFind.append(wherePhrase);
		Query query = createQuery(sbFind);

		query.setParameter("refInstansiId", refInstansi.getKode());
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public List<DtPendaftaran> findByInstansiAndMap(RefInstansi instansi,
	        Map<String, Object> map, int... idxAndCount) {
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());
		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.formasi");
		StringBuilder wherePhrase = new StringBuilder(
		        " WHERE (model.formasi.refInstansi.kode = :refInstansiId) ");
		String whereMap = "";
		Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Object> entry = entries.next();
			whereMap = whereMap + "model." + entry.getKey() + " LIKE :" + entry.getKey();
		}
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase)
		        .append(" AND " + whereMap);
		System.out.println("Query : " + sbFind);
		Query query = createQuery(sbFind);
		query.setParameter("refInstansiId", instansi.getKode());

		Iterator<Map.Entry<String, Object>> entries2 = map.entrySet().iterator();
		while (entries2.hasNext()) {
			Map.Entry<String, Object> entry = entries2.next();
			query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
		}
		return doQuery(query, idxAndCount);
	}

	@Override
	public Integer countByInstansiAndMap(RefInstansi refInstansi, Map<String, Object> map) {
		StringBuilder sbFind = new StringBuilder(
		        "SELECT COUNT(model.id) FROM DtPendaftaran model ");
		StringBuilder wherePhrase = new StringBuilder(
		        " WHERE model.formasi.refInstansi.kode = :refInstansiId ");

		String whereMap = "";
		Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Object> entry = entries.next();
			whereMap = whereMap + "model." + entry.getKey() + " LIKE :" + entry.getKey();
		}
		sbFind.append(wherePhrase).append(" AND " + whereMap);
		System.out.println("Query : " + sbFind);
		Query query = createQuery(sbFind);
		query.setParameter("refInstansiId", refInstansi.getKode());

		Iterator<Map.Entry<String, Object>> entries2 = map.entrySet().iterator();
		while (entries2.hasNext()) {
			Map.Entry<String, Object> entry = entries2.next();
			query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}
}
