package org.sscn.dao.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Order;

import org.hibernate.Criteria;
import org.hibernate.Query;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sscn.core.persistence.dao.impl.CoreDaoImpl;
import org.sscn.core.persistence.tools.PropCriteriaAndValue;
import org.sscn.core.persistence.tools.QueryOrder;
import org.sscn.dao.MFormasiDao;
import org.sscn.persistence.entities.MFormasi;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.persistence.entities.RefJenisFormasi;

@Repository("MFormasiDao")
public class MFormasiDaoImpl extends CoreDaoImpl<MFormasi> implements
		MFormasiDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public MFormasiDaoImpl(SessionFactory sessionFactory) {
		super(MFormasi.class, sessionFactory);
	}


	@Override
	public List<MFormasi> findFormasiUmumByLokasiAndNamaJabatan(String lokasiId, String namaJabatan, List<QueryOrder> orders, int... idxAndCount){
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());

		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.refJabatan","model.refLokasi");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE (model.refLokasi.kode = :refLokasiId) AND model.refJenisFormasi.id=1 ");
		
		if (namaJabatan != null){
			wherePhrase.append(" AND lower(model.refJabatan.nama) like :namaJabatan ");
		}
		
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase);
		if (orders != null){
			sbFind.append(createQueryOrderPhrase(orders));
		}
		Query query = createQuery(sbFind);
		query.setParameter("refLokasiId", lokasiId);
		if (namaJabatan != null){
			query.setParameter("namaJabatan", "%"+namaJabatan.toLowerCase()+"%");
		}		
		
		return doQuery(query, idxAndCount);
	}

	@Override
	public Integer countFormasiUmumByLokasiAndNamaJabatan(String lokasiId, String namaJabatan){
		StringBuilder sbFind = new StringBuilder(
				"SELECT COUNT(model.id) FROM MFormasi model ");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE model.refLokasi.kode = :refLokasiId ");
		
		if (namaJabatan != null){
			wherePhrase.append(" AND lower(model.refJabatan.nama) like :namaJabatan ");
		}
		sbFind.append(wherePhrase);
		Query query = createQuery(sbFind);
		
		query.setParameter("refLokasiId", lokasiId);
		if (namaJabatan != null){
			query.setParameter("namaJabatan", "%"+namaJabatan.toLowerCase()+"%");
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@Override
	public List<MFormasi> findFormasiUmum(String instansiId, List<QueryOrder> orders, int... idxAndCount){
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());
		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.refJabatan","model.refLokasi");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE (model.refInstansi.kode = :refInstansiId) AND (model.refJenisFormasi.id=1 or model.refJenisFormasi.id is null)");
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase);
		if (orders != null){
			sbFind.append(createQueryOrderPhrase(orders));
		}
		Query query = createQuery(sbFind);
		query.setParameter("refInstansiId", instansiId);
		
		return doQuery(query, idxAndCount);
	}

	@Override
	public Integer countFormasiUmum(String instansiId){
		StringBuilder sbFind = new StringBuilder(
				"SELECT COUNT(model.id) FROM MFormasi model ");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE model.refInstansi.kode = :refInstansiId AND (model.refJenisFormasi.id=1 or model.refJenisFormasi.id is null)");
		
		sbFind.append(wherePhrase);
		Query query = createQuery(sbFind);
		
		query.setParameter("refInstansiId", instansiId);
		
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@Override
	public Integer countNumberOfFormasiUmum(String instansiId){
		StringBuilder sbFind = new StringBuilder(
				"SELECT SUM(model.jumlah) FROM MFormasi model ");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE model.refInstansi.kode = :refInstansiId AND (model.refJenisFormasi.id=1 or model.refJenisFormasi.id is null)");
		
		sbFind.append(wherePhrase);
		Query query = createQuery(sbFind);
		
		query.setParameter("refInstansiId", instansiId);
		
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@Override
	public List<MFormasi> findFormasiKhusus(String instansiId, List<QueryOrder> orders, int... idxAndCount){
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());

		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.refJabatan","model.refLokasi");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE (model.refInstansi.kode = :refInstansiId) AND (model.refJenisFormasi.id<>1 or model.refJenisFormasi.id is null)");
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase);
		if (orders != null){
			sbFind.append(createQueryOrderPhrase(orders));
		}
		Query query = createQuery(sbFind);
		query.setParameter("refInstansiId", instansiId);
		
		return doQuery(query, idxAndCount);
	}

	@Override
	public Integer countFormasiKhusus(String instansiId){
		StringBuilder sbFind = new StringBuilder(
				"SELECT COUNT(model.id) FROM MFormasi model ");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE model.refInstansi.kode = :refInstansiId AND (model.refJenisFormasi.id<>1 or model.refJenisFormasi.id is null)");
		
		sbFind.append(wherePhrase);
		Query query = createQuery(sbFind);
		
		query.setParameter("refInstansiId", instansiId);
		
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@Override
	public List<MFormasi> findFormasiKhususUsingFilter(List<PropCriteriaAndValue> filterList, List<QueryOrder> orders, int... idxAndCount){
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());

		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.refJabatan","model.refLokasi","model.refJenisFormasi");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE (model.refJenisFormasi.id<>1 or model.refJenisFormasi.id is null)");
		sbFind.append(innerJoinFetchPhrase);
		if (filterList != null){
			int  i=0;
			for (PropCriteriaAndValue filter : filterList){
				wherePhrase.append(" AND " + "model."+filter.getPropertyName() + filter.getComparator() + ":par"+Integer.toString(i));
				i++;
			}
		}
		sbFind.append(wherePhrase);
		if (orders != null){
			sbFind.append(createQueryOrderPhrase(orders));
		}
		Query query = createQuery(sbFind);
		if (filterList != null){
			int i=0;
			for (PropCriteriaAndValue filter : filterList){
				query.setParameter("par" + Integer.toString(i), filter.getValue());
				i++;
			}
		}
				
		return doQuery(query, idxAndCount);
	}
	
	@Override
	public Integer countFormasiKhususUsingFilter(List<PropCriteriaAndValue> filterList){
		StringBuilder sbFind = new StringBuilder(
				"SELECT COUNT(model.id) FROM MFormasi model ");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE (model.refJenisFormasi.id<>1 or model.refJenisFormasi.id is null)");
		
		if (filterList != null){
			int  i= 0;
			for (PropCriteriaAndValue filter : filterList){
				wherePhrase.append(" AND " + "model."+filter.getPropertyName() + filter.getComparator() + ":par"+Integer.toString(i));
				i++;
			}
		}
		sbFind.append(wherePhrase);
		
		Query query = createQuery(sbFind);
		if (filterList != null){
			int i = 0;
			for (PropCriteriaAndValue filter : filterList){
				query.setParameter("par"+Integer.toString(i), filter.getValue());
				i++;
			}
		}
		
		return Integer.valueOf(query.uniqueResult().toString());
	}


	@Override
	public List<MFormasi> findByInstansiLokasiPendidikan(String instansi,
			String lokasi, String pendidikan) {
		/*
		 * SELECT m.jabatan FROM m_formasi m INNER JOIN dt_formasi dt ON
		 * dt.formasi=m.id WHERE m.instansi = '4011' AND m.lokasi = '1234' AND
		 * dt.pendidikan = '2110303';
		 */

		StringBuilder sbFind = new StringBuilder("SELECT model FROM MFormasi as model, DtFormasi as detail ");
		sbFind.append("WHERE model.id = detail.formasi.id ");
		sbFind.append("AND model.refInstansi.kode = :instansi ");
		sbFind.append("AND model.refLokasi.kode = :lokasi ");
		sbFind.append("AND detail.pendidikan.kode = :pendidikan ");
		
		Query query = createQuery(sbFind);
		query.setParameter("instansi", instansi);
		query.setParameter("lokasi", lokasi);
		query.setParameter("pendidikan", pendidikan);

		return doQuery(query, null);
	}

}
