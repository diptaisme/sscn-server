package org.sscn.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.sscn.persistence.entities.view.DataPendaftaran;
import org.sscn.persistence.entities.view.DataPendaftaran2014;
import org.sscn.persistence.entities.view.RekapanPendaftaran;
import org.sscn.persistence.entities.view.RekapanPendaftaranTidakLulus;
import org.sscn.persistence.entities.view.StatInstansi;
import org.sscn.persistence.entities.view.StatInstansiJabatan;

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
	public List<DtPendaftaran> findByInstansi(RefInstansi instansi,
			int... idxAndCount) {
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());

		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.formasi");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE (model.formasi.refInstansi.kode = :refInstansiId) order by model.tglLahir, model.nilaiIpk DESC");
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase);

		Query query = createQuery(sbFind);

		query.setParameter("refInstansiId", instansi.getKode());
		return doQuery(query, idxAndCount);
	}

	@Override
	public List<DtPendaftaran> findReverifikasiByInstansi(RefInstansi instansi,
			int... idxAndCount) {
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());

		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.formasi");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE (model.formasi.refInstansi.kode = :refInstansiId) and (status = '1' or status = '0')  order by model.tglLahir, model.nilaiIpk DESC");
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase);

		Query query = createQuery(sbFind);

		query.setParameter("refInstansiId", instansi.getKode());
		return doQuery(query, idxAndCount);
	}

	@Override
	public String getnoUrutPendaftaran(String limaDigitPertama) {

		StringBuilder sqlText = new StringBuilder(
				"select max(convert(substr(NO_PESERTA,6,5),unsigned integer)) from dt_pendaftaran where NO_PESERTA LIKE '"
						+ limaDigitPertama + "%'");

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
	public Integer countReverifikasiByInstansi(RefInstansi refInstansi) {
		StringBuilder sbFind = new StringBuilder(
				"SELECT COUNT(model.id) FROM DtPendaftaran model ");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE model.formasi.refInstansi.kode = :refInstansiId and (status='1' or status='0')");
		sbFind.append(wherePhrase);
		Query query = createQuery(sbFind);

		query.setParameter("refInstansiId", refInstansi.getKode());
		return Integer.valueOf(query.uniqueResult().toString());
	}

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
			whereMap = whereMap + "model." + entry.getKey() + " LIKE :"
					+ entry.getKey();
		}
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase)
				.append(" AND " + whereMap);
		System.out.println("Query : " + sbFind);
		Query query = createQuery(sbFind);
		query.setParameter("refInstansiId", instansi.getKode());

		Iterator<Map.Entry<String, Object>> entries2 = map.entrySet()
				.iterator();
		while (entries2.hasNext()) {
			Map.Entry<String, Object> entry = entries2.next();
			query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
		}
		return doQuery(query, idxAndCount);
	}

	@Override
	public Integer countByInstansiAndMap(RefInstansi refInstansi,
			Map<String, Object> map) {
		StringBuilder sbFind = new StringBuilder(
				"SELECT COUNT(model.id) FROM DtPendaftaran model ");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE model.formasi.refInstansi.kode = :refInstansiId ");

		String whereMap = "";
		Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Object> entry = entries.next();
			whereMap = whereMap + "model." + entry.getKey() + " LIKE :"
					+ entry.getKey();
		}
		sbFind.append(wherePhrase).append(" AND " + whereMap);
		// System.out.println("Query : " + sbFind);
		Query query = createQuery(sbFind);
		query.setParameter("refInstansiId", refInstansi.getKode());

		Iterator<Map.Entry<String, Object>> entries2 = map.entrySet()
				.iterator();
		while (entries2.hasNext()) {
			Map.Entry<String, Object> entry = entries2.next();
			query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public List<DtPendaftaran> findByInstansiAndMapFilterVerify(
			RefInstansi instansi, Map<String, Object> map, int... idxAndCount) {
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());
		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.formasi");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE (model.formasi.refInstansi.kode = :refInstansiId) ");
		// StringBuilder wherePhrase = new StringBuilder(
		// " WHERE (model.formasi.refInstansi.kode = :refInstansiId) AND model.status = '' ");
		String whereMap = "";

		Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
		// String umur =
		// "((year(curdate())-year(model.tglLahir)) - (right(curdate(),5) < right(model.tglLahir,5)))";
		int i = 1;
		while (entries.hasNext()) {
			Map.Entry<String, Object> entry = entries.next();
			if (entry.getKey().contentEquals("tkPendidikan")) {
				whereMap = whereMap
						+ "model.formasi.id in (select model2.formasi.id from DtFormasi model2 inner join model2.formasi inner join model2.pendidikan where model2.pendidikan.tingkat = :tkPendidikan and model2.formasi.refInstansi.kode = :refInstansiId)";
			}
			if (entry.getKey().contentEquals("fromUsia")) {
				whereMap = whereMap + "model.tglLahir" + " <= :"
						+ entry.getKey();
			}
			if (entry.getKey().contentEquals("toUsia")) {
				whereMap = whereMap + "model.tglLahir" + " >= :"
						+ entry.getKey();
			}
			if (entry.getKey().contentEquals("fromIPK")) {
				whereMap = whereMap + "model.nilaiIpk" + " >= :"
						+ entry.getKey();
			}
			if (entry.getKey().contentEquals("toIPK")) {
				whereMap = whereMap + "model.nilaiIpk" + " <= :"
						+ entry.getKey();
			}
			if (i < map.size()) {
				whereMap = whereMap + " AND ";
			}
			i++;
		}
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase)
				.append(" AND " + whereMap)
				.append(" order by model.nilaiIpk, model.tglLahir ");

		Query query = createQuery(sbFind);
		query.setParameter("refInstansiId", instansi.getKode());

		Iterator<Map.Entry<String, Object>> entries2 = map.entrySet()
				.iterator();
		while (entries2.hasNext()) {
			Map.Entry<String, Object> entry = entries2.next();
			if (entry.getKey().contentEquals("fromUsia")
					|| entry.getKey().contentEquals("toUsia")) {
				Calendar c = Calendar.getInstance();
				int cdate = -(Integer.parseInt((String) entry.getValue()));
				c.add(Calendar.YEAR, cdate);
				query.setParameter(entry.getKey(), c.getTime());
			} else {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		// System.out.println("Query : " + query.get);
		return doQuery(query, idxAndCount);
	}

	@Override
	public Integer countByInstansiAndMapFilterVerify(RefInstansi refInstansi,
			Map<String, Object> map) {
		StringBuilder sbFind = new StringBuilder(
				"SELECT COUNT(model.id) FROM DtPendaftaran model ");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE model.formasi.refInstansi.kode = :refInstansiId ");

		String whereMap = "";
		Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
		int i = 1;
		while (entries.hasNext()) {
			Map.Entry<String, Object> entry = entries.next();
			if (entry.getKey().contentEquals("tkPendidikan")) {
				whereMap = whereMap
						+ "model.formasi.id in (select model2.formasi.id from DtFormasi model2 inner join model2.formasi inner join model2.pendidikan where model2.pendidikan.tingkat = :tkPendidikan and model2.formasi.refInstansi.kode = :refInstansiId)";
			}
			if (entry.getKey().contentEquals("fromUsia")) {
				whereMap = whereMap + "model.tglLahir" + " <= :"
						+ entry.getKey();
			}
			if (entry.getKey().contentEquals("toUsia")) {
				whereMap = whereMap + "model.tglLahir" + " >= :"
						+ entry.getKey();
			}
			if (entry.getKey().contentEquals("fromIPK")) {
				whereMap = whereMap + "model.nilaiIpk" + " >= :"
						+ entry.getKey();
			}
			if (entry.getKey().contentEquals("toIPK")) {
				whereMap = whereMap + "model.nilaiIpk" + " <= :"
						+ entry.getKey();
			}
			if (i < map.size()) {
				whereMap = whereMap + " AND ";
			}
			i++;
		}

		sbFind.append(wherePhrase).append(" AND " + whereMap);
		// System.out.println("Query : " + sbFind);
		Query query = createQuery(sbFind);
		query.setParameter("refInstansiId", refInstansi.getKode());

		Iterator<Map.Entry<String, Object>> entries2 = map.entrySet()
				.iterator();
		while (entries2.hasNext()) {
			Map.Entry<String, Object> entry = entries2.next();
			if (entry.getKey().contentEquals("fromUsia")
					|| entry.getKey().contentEquals("toUsia")) {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.YEAR,
						-(Integer.parseInt((String) entry.getValue())));
				query.setParameter(entry.getKey(), c.getTime());
			} else {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public List<DataPendaftaran> findDataPendaftaran(String kodeInstansi) {
		List<DataPendaftaran> result = new ArrayList<DataPendaftaran>();
		final String sql = "SELECT NO_NIK, NO_REGISTER, NO_PESERTA, NAMA, TMP_LAHIR, DATE_FORMAT(TGL_LAHIR, '%d-%m-%Y'), JNS_KELAMIN, ALAMAT, KODE_POS, PROPINSI, KOTA, TELPON, EMAIL, LEMBAGA, NO_IJAZAH, AKREDITASI, NILAI_IPK, INSTANSI, NAMA_INSTANSI as NI, LOKASI, NAMA_LOKASI as NL, JABATAN, NAMA_JABATAN as NJ, PENDIDIKAN, NAMA_PENDIDIKAN as NP, DATE_FORMAT(TGL_CREATED, '%d-%m-%Y %T'), STATUS FROM data_01 "
				+ "WHERE INSTANSI= '"
				+ kodeInstansi
				+ "' order by TGL_CREATED, NO_PESERTA, NAMA";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					DataPendaftaran dataPendaftaran = new DataPendaftaran();
					int index = 0;
					dataPendaftaran.setNoNik(String.valueOf(obj[index++]));
					dataPendaftaran.setNoRegister(String.valueOf(obj[index++]));
					dataPendaftaran.setNoPeserta(String.valueOf(obj[index++]));
					dataPendaftaran.setNama(String.valueOf(obj[index++]));
					dataPendaftaran.setTmpLahir(String.valueOf(obj[index++]));
					dataPendaftaran.setTglLahir(String.valueOf(obj[index++]));
					dataPendaftaran.setJnsKelamin(String.valueOf(obj[index++]));
					dataPendaftaran.setAlamat(String.valueOf(obj[index++]));
					dataPendaftaran.setKodePos(String.valueOf(obj[index++]));
					dataPendaftaran.setPropinsi(String.valueOf(obj[index++]));
					dataPendaftaran.setKota(String.valueOf(obj[index++]));
					dataPendaftaran.setTelpon(String.valueOf(obj[index++]));
					dataPendaftaran.setEmail(String.valueOf(obj[index++]));
					dataPendaftaran.setAsalInstitusiPendidikan(String
							.valueOf(obj[index++]));
					dataPendaftaran.setNoIjazah(String.valueOf(obj[index++]));
					dataPendaftaran.setAkreditasi(String.valueOf(obj[index++]));
					dataPendaftaran.setNilaiIpk(String.valueOf(obj[index++]));
					dataPendaftaran.setInstansiKode(String
							.valueOf(obj[index++]));
					dataPendaftaran.setInstansiNama(String
							.valueOf(obj[index++]));
					dataPendaftaran.setLokasiKode(String.valueOf(obj[index++]));
					dataPendaftaran.setLokasiNama(String.valueOf(obj[index++]));
					dataPendaftaran
							.setJabatanKode(String.valueOf(obj[index++]));
					dataPendaftaran
							.setJabatanNama(String.valueOf(obj[index++]));
					dataPendaftaran.setPendidikanKode(String
							.valueOf(obj[index++]));
					dataPendaftaran.setPendidikanNama(String
							.valueOf(obj[index++]));
					dataPendaftaran.setTglCreated(String.valueOf(obj[index++]));
					dataPendaftaran.setStatus(String.valueOf(obj[index++]));

					result.add(dataPendaftaran);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<DataPendaftaran> findDataPesertaTest(String kodeInstansi) {
		List<DataPendaftaran> result = new ArrayList<DataPendaftaran>();
		final String sql = "SELECT NO_NIK, NO_REGISTER, NO_PESERTA, NAMA, TMP_LAHIR, DATE_FORMAT(TGL_LAHIR, '%d-%m-%y'), JNS_KELAMIN, ALAMAT, KODE_POS, PROPINSI, KOTA, TELPON, EMAIL, LEMBAGA, NO_IJAZAH, AKREDITASI, NILAI_IPK, INSTANSI, NAMA_INSTANSI as NI, LOKASI, NAMA_LOKASI as NL, JABATAN, NAMA_JABATAN as NJ, PENDIDIKAN, NAMA_PENDIDIKAN as NP, DATE_FORMAT(TGL_CREATED, '%d-%m-%y'), STATUS FROM data_01 "
				+ "WHERE INSTANSI= '"
				+ kodeInstansi
				+ "' and STATUS = '1' order by NO_PESERTA, NAMA";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					DataPendaftaran dataPendaftaran = new DataPendaftaran();
					int index = 0;
					dataPendaftaran.setNoNik(String.valueOf(obj[index++]));
					dataPendaftaran.setNoRegister(String.valueOf(obj[index++]));
					dataPendaftaran.setNoPeserta(String.valueOf(obj[index++]));
					dataPendaftaran.setNama(String.valueOf(obj[index++]));
					dataPendaftaran.setTmpLahir(String.valueOf(obj[index++]));
					dataPendaftaran.setTglLahir(String.valueOf(obj[index++]));
					dataPendaftaran.setJnsKelamin(String.valueOf(obj[index++]));
					dataPendaftaran.setAlamat(String.valueOf(obj[index++]));
					dataPendaftaran.setKodePos(String.valueOf(obj[index++]));
					dataPendaftaran.setPropinsi(String.valueOf(obj[index++]));
					dataPendaftaran.setKota(String.valueOf(obj[index++]));
					dataPendaftaran.setTelpon(String.valueOf(obj[index++]));
					dataPendaftaran.setEmail(String.valueOf(obj[index++]));
					dataPendaftaran.setAsalInstitusiPendidikan(String
							.valueOf(obj[index++]));
					dataPendaftaran.setNoIjazah(String.valueOf(obj[index++]));
					dataPendaftaran.setAkreditasi(String.valueOf(obj[index++]));
					dataPendaftaran.setNilaiIpk(String.valueOf(obj[index++]));
					dataPendaftaran.setInstansiKode(String
							.valueOf(obj[index++]));
					dataPendaftaran.setInstansiNama(String
							.valueOf(obj[index++]));
					dataPendaftaran.setLokasiKode(String.valueOf(obj[index++]));
					dataPendaftaran.setLokasiNama(String.valueOf(obj[index++]));
					dataPendaftaran
							.setJabatanKode(String.valueOf(obj[index++]));
					dataPendaftaran
							.setJabatanNama(String.valueOf(obj[index++]));
					dataPendaftaran.setPendidikanKode(String
							.valueOf(obj[index++]));
					dataPendaftaran.setPendidikanNama(String
							.valueOf(obj[index++]));
					dataPendaftaran.setTglCreated(String.valueOf(obj[index++]));
					dataPendaftaran.setStatus(String.valueOf(obj[index++]));

					result.add(dataPendaftaran);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<StatInstansi> getStatistikPendaftaranInstansi() {
		List<StatInstansi> result = new ArrayList<StatInstansi>();
		final String sql = "SELECT INSTANSI, Jml_Pendaftar, Jml_Lulus, Jml_tdkLulus from tab_stat_01 order by INSTANSI";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					StatInstansi statInstansi = new StatInstansi();
					int index = 0;
					statInstansi.setInstansi(String.valueOf(obj[index++]));
					statInstansi.setJumlahPendaftar(String
							.valueOf(obj[index++]));
					statInstansi.setJumlahLulus(String.valueOf(obj[index++]));
					statInstansi.setJumlahTidakLulus(String
							.valueOf(obj[index++]));
					result.add(statInstansi);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<StatInstansiJabatan> getStatistikJabatanPendaftaranInstansi(
			String kodeInstansi) {
		List<StatInstansiJabatan> result = new ArrayList<StatInstansiJabatan>();
		final String sql = "SELECT INSTANSI, NAMA, Jml_Pendaftar, Jml_Lulus, Jml_tdkLulus from tab_stat_02 where INSTANSI='"
				+ kodeInstansi + "' order by NAMA";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					StatInstansiJabatan statInstansi = new StatInstansiJabatan();
					int index = 0;
					statInstansi.setInstansi(String.valueOf(obj[index++]));
					statInstansi.setJabatan(String.valueOf(obj[index++]));
					statInstansi.setJumlahPendaftar(String
							.valueOf(obj[index++]));
					statInstansi.setJumlahLulus(String.valueOf(obj[index++]));
					statInstansi.setJumlahTidakLulus(String
							.valueOf(obj[index++]));
					result.add(statInstansi);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<StatInstansi> getStatistikPendaftaranInstansi(
			String kodeInstansi) {
		List<StatInstansi> result = new ArrayList<StatInstansi>();
		final String sql = "SELECT INSTANSI, Jml_Pendaftar, Jml_Lulus, Jml_tdkLulus from tab_stat_01 where INSTANSI='"
				+ kodeInstansi + "'";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					StatInstansi statInstansi = new StatInstansi();
					int index = 0;
					statInstansi.setInstansi(String.valueOf(obj[index++]));
					statInstansi.setJumlahPendaftar(String
							.valueOf(obj[index++]));
					statInstansi.setJumlahLulus(String.valueOf(obj[index++]));
					statInstansi.setJumlahTidakLulus(String
							.valueOf(obj[index++]));
					result.add(statInstansi);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<RekapanPendaftaran> getRekapanPendaftaranInstansi(
			String kodeInstansi) {
		List<RekapanPendaftaran> result = new ArrayList<RekapanPendaftaran>();
		final String sql = "SELECT lokasi, jabatan, pendidikan, TOTAL_PENDAFTAR, LULUS, TIDAK_LULUS, BELUM_VERIFIKASI from rekap_lok_jab_pend where kode='"
				+ kodeInstansi + "'";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			String lokasi = "";
			String jabatan = "";
			for (Object[] obj : res) {
				if (res != null) {
					RekapanPendaftaran rekapanPendaftaran = new RekapanPendaftaran();
					int index = 0;
					String lokasiTemp = String.valueOf(obj[index++]);
					String jabatanTemp = String.valueOf(obj[index++]);
					if (lokasi.equals(lokasiTemp)) {
						rekapanPendaftaran.setLokasi("");
					} else if (lokasi.equals("")) {
						rekapanPendaftaran.setLokasi(lokasiTemp);
						lokasi = lokasiTemp;
					} else {
						rekapanPendaftaran.setLokasi(lokasiTemp);
						lokasi = lokasiTemp;
					}
					if (jabatan.equals(jabatanTemp)) {
						rekapanPendaftaran.setJabatan("");
					} else if (jabatan.equals("")) {
						rekapanPendaftaran.setJabatan(jabatanTemp);
						jabatan = jabatanTemp;
					} else {
						rekapanPendaftaran.setJabatan(jabatanTemp);
						jabatan = jabatanTemp;
					}
					rekapanPendaftaran.setPendidikan(String
							.valueOf(obj[index++]));
					rekapanPendaftaran.setJumlahPendaftar(String
							.valueOf(obj[index++]));
					rekapanPendaftaran.setJumlahLulus(String
							.valueOf(obj[index++]));
					rekapanPendaftaran.setJumlahTidakLulus(String
							.valueOf(obj[index++]));
					rekapanPendaftaran.setJumlahBelumVerifikasi(String
							.valueOf(obj[index++]));
					result.add(rekapanPendaftaran);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<DtPendaftaran> findReverifikasiByInstansiAndMap(
			RefInstansi instansi, Map<String, Object> map, int... idxAndCount) {
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());
		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.formasi");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE (model.formasi.refInstansi.kode = :refInstansiId) AND (status='0' or status='1') ");
		String whereMap = "";
		Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Object> entry = entries.next();
			whereMap = whereMap + "model." + entry.getKey() + " = :"
					+ entry.getKey();
		}
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase)
				.append(" AND " + whereMap);
		// System.out.println("Query : " + sbFind);
		Query query = createQuery(sbFind);
		query.setParameter("refInstansiId", instansi.getKode());

		Iterator<Map.Entry<String, Object>> entries2 = map.entrySet()
				.iterator();
		while (entries2.hasNext()) {
			Map.Entry<String, Object> entry = entries2.next();
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return doQuery(query, idxAndCount);
	}

	@Override
	public Integer countReverifikasiByInstansiAndMap(RefInstansi refInstansi,
			Map<String, Object> map) {
		StringBuilder sbFind = new StringBuilder(
				"SELECT COUNT(model.id) FROM DtPendaftaran model ");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE model.formasi.refInstansi.kode = :refInstansiId AND (status='1' or status='0') ");

		String whereMap = "";
		Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Object> entry = entries.next();
			whereMap = whereMap + "model." + entry.getKey() + " LIKE :"
					+ entry.getKey();
		}
		sbFind.append(wherePhrase).append(" AND " + whereMap);
		Query query = createQuery(sbFind);
		query.setParameter("refInstansiId", refInstansi.getKode());

		Iterator<Map.Entry<String, Object>> entries2 = map.entrySet()
				.iterator();
		while (entries2.hasNext()) {
			Map.Entry<String, Object> entry = entries2.next();
			query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	@Override
	public List<RekapanPendaftaranTidakLulus> getRekapanPendaftaranTidakLulusInstansi(
			String kodeInstansi) {
		List<RekapanPendaftaranTidakLulus> result = new ArrayList<RekapanPendaftaranTidakLulus>();
		final String sql = "select dt_pendaftaran.NAMA, dt_pendaftaran.NO_NIK, dt_persyaratan.syarat, dt_persyaratan.instansi  from dt_verifikasi_nok "
				+ "	inner join dt_pendaftaran ON dt_verifikasi_nok.PENDAFTAR = dt_pendaftaran.ID "
				+ "	inner join dt_persyaratan ON dt_verifikasi_nok.PERSYARATAN = dt_persyaratan.id "
				+ "where dt_persyaratan.instansi = '"
				+ kodeInstansi
				+ "' order by dt_pendaftaran.NAMA, dt_persyaratan.urutan";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			// String lokasi = "";
			// String jabatan = "";
			for (Object[] obj : res) {
				if (res != null) {
					RekapanPendaftaranTidakLulus rekapanPendaftaran = new RekapanPendaftaranTidakLulus();
					int index = 0;
					String nama = String.valueOf(obj[index++]);
					String nik = String.valueOf(obj[index++]);
					String syarat = String.valueOf(obj[index++]);
					rekapanPendaftaran.setNama(nama);
					rekapanPendaftaran.setNik(nik);
					rekapanPendaftaran.setSyarat(syarat);
					result.add(rekapanPendaftaran);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	// sscn 2014
	@Override
	public List<DataPendaftaran2014> findDataPendaftaran2014(String kodeInstansi) {
		List<DataPendaftaran2014> result = new ArrayList<DataPendaftaran2014>();
		final String sql = "SELECT NO_NIK, NO_REGISTER, NO_PESERTA, NAMA, TMP_LAHIR, DATE_FORMAT(TGL_LAHIR, '%d-%m-%Y'), JNS_KELAMIN, ALAMAT, KODE_POS, PROPINSI, KOTA, TELPON, EMAIL, LEMBAGA, NO_IJAZAH, AKREDITASI, NILAI_IPK, INSTANSI, NAMA_INSTANSI as NI, LOKASI, NAMA_LOKASI as NL, JABATAN, NAMA_JABATAN as NJ, PENDIDIKAN, NAMA_PENDIDIKAN as NP, DATE_FORMAT(TGL_CREATED, '%d-%m-%Y %T'), "
				+ "STATUS, LOKASI2,  NAMA_LOKASI2, JABATAN2,  NAMA_JABATAN2, "
				+ "LOKASI3,  NAMA_LOKASI3,  JABATAN3,  NAMA_JABATAN3, "
				+ "LOKASI_TEST, NAMA_LOKASI_TEST "
				+ "FROM data_01_2014 "
				+ "WHERE INSTANSI= '"
				+ kodeInstansi
				+ "' order by TGL_CREATED, NO_PESERTA, NAMA";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					DataPendaftaran2014 dataPendaftaran = new DataPendaftaran2014();
					int index = 0;
					dataPendaftaran.setNoNik(String.valueOf(obj[index++]));
					dataPendaftaran.setNoRegister(String.valueOf(obj[index++]));
					dataPendaftaran.setNoPeserta(String.valueOf(obj[index++]));
					dataPendaftaran.setNama(String.valueOf(obj[index++]));
					dataPendaftaran.setTmpLahir(String.valueOf(obj[index++]));
					dataPendaftaran.setTglLahir(String.valueOf(obj[index++]));
					dataPendaftaran.setJnsKelamin(String.valueOf(obj[index++]));
					dataPendaftaran.setAlamat(String.valueOf(obj[index++]));
					dataPendaftaran.setKodePos(String.valueOf(obj[index++]));
					dataPendaftaran.setPropinsi(String.valueOf(obj[index++]));
					dataPendaftaran.setKota(String.valueOf(obj[index++]));
					dataPendaftaran.setTelpon(String.valueOf(obj[index++]));
					dataPendaftaran.setEmail(String.valueOf(obj[index++]));
					dataPendaftaran.setAsalInstitusiPendidikan(String
							.valueOf(obj[index++]));
					dataPendaftaran.setNoIjazah(String.valueOf(obj[index++]));
					dataPendaftaran.setAkreditasi(String.valueOf(obj[index++]));
					dataPendaftaran.setNilaiIpk(String.valueOf(obj[index++]));
					dataPendaftaran.setInstansiKode(String
							.valueOf(obj[index++]));
					dataPendaftaran.setInstansiNama(String
							.valueOf(obj[index++]));
					dataPendaftaran.setLokasiKode(String.valueOf(obj[index++]));
					dataPendaftaran.setLokasiNama(String.valueOf(obj[index++]));
					dataPendaftaran
							.setJabatanKode(String.valueOf(obj[index++]));
					dataPendaftaran
							.setJabatanNama(String.valueOf(obj[index++]));
					dataPendaftaran.setPendidikanKode(String
							.valueOf(obj[index++]));
					dataPendaftaran.setPendidikanNama(String
							.valueOf(obj[index++]));
					dataPendaftaran.setTglCreated(String.valueOf(obj[index++]));
					dataPendaftaran.setStatus(String.valueOf(obj[index++]));
					//
					dataPendaftaran
							.setLokasiKode2(String.valueOf(obj[index++]));
					dataPendaftaran
							.setLokasiNama2(String.valueOf(obj[index++]));
					dataPendaftaran.setJabatanKode2(String
							.valueOf(obj[index++]));
					dataPendaftaran.setJabatanNama2(String
							.valueOf(obj[index++]));
					dataPendaftaran
							.setLokasiKode3(String.valueOf(obj[index++]));
					dataPendaftaran
							.setLokasiNama3(String.valueOf(obj[index++]));
					dataPendaftaran.setJabatanKode3(String
							.valueOf(obj[index++]));
					dataPendaftaran.setJabatanNama3(String
							.valueOf(obj[index++]));
					dataPendaftaran.setLokasiTest(String.valueOf(obj[index++]));
					dataPendaftaran.setLokasiTestNama(String
							.valueOf(obj[index++]));

					result.add(dataPendaftaran);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<DataPendaftaran2014> findDataPesertaTest2014(String kodeInstansi) {
		List<DataPendaftaran2014> result = new ArrayList<DataPendaftaran2014>();
		final String sql = "SELECT NO_NIK, NO_REGISTER, NO_PESERTA, NAMA, TMP_LAHIR, DATE_FORMAT(TGL_LAHIR, '%d-%m-%Y'), JNS_KELAMIN, ALAMAT, KODE_POS, PROPINSI, KOTA, TELPON, EMAIL, LEMBAGA, NO_IJAZAH, AKREDITASI, NILAI_IPK, INSTANSI, NAMA_INSTANSI as NI, LOKASI, NAMA_LOKASI as NL, JABATAN, NAMA_JABATAN as NJ, PENDIDIKAN, NAMA_PENDIDIKAN as NP, DATE_FORMAT(TGL_CREATED, '%d-%m-%Y %T'), "
				+ "STATUS, LOKASI2,  NAMA_LOKASI2, JABATAN2,  NAMA_JABATAN2, "
				+ "LOKASI3,  NAMA_LOKASI3,  JABATAN3,  NAMA_JABATAN3, "
				+ "LOKASI_TEST, NAMA_LOKASI_TEST "
				+ "FROM data_01_2014 "
				+ "WHERE INSTANSI= '"
				+ kodeInstansi
				+ "' and STATUS = '1' order by NO_PESERTA, NAMA";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					DataPendaftaran2014 dataPendaftaran = new DataPendaftaran2014();
					int index = 0;
					dataPendaftaran.setNoNik(String.valueOf(obj[index++]));
					dataPendaftaran.setNoRegister(String.valueOf(obj[index++]));
					dataPendaftaran.setNoPeserta(String.valueOf(obj[index++]));
					dataPendaftaran.setNama(String.valueOf(obj[index++]));
					dataPendaftaran.setTmpLahir(String.valueOf(obj[index++]));
					dataPendaftaran.setTglLahir(String.valueOf(obj[index++]));
					dataPendaftaran.setJnsKelamin(String.valueOf(obj[index++]));
					dataPendaftaran.setAlamat(String.valueOf(obj[index++]));
					dataPendaftaran.setKodePos(String.valueOf(obj[index++]));
					dataPendaftaran.setPropinsi(String.valueOf(obj[index++]));
					dataPendaftaran.setKota(String.valueOf(obj[index++]));
					dataPendaftaran.setTelpon(String.valueOf(obj[index++]));
					dataPendaftaran.setEmail(String.valueOf(obj[index++]));
					dataPendaftaran.setAsalInstitusiPendidikan(String
							.valueOf(obj[index++]));
					dataPendaftaran.setNoIjazah(String.valueOf(obj[index++]));
					dataPendaftaran.setAkreditasi(String.valueOf(obj[index++]));
					dataPendaftaran.setNilaiIpk(String.valueOf(obj[index++]));
					dataPendaftaran.setInstansiKode(String
							.valueOf(obj[index++]));
					dataPendaftaran.setInstansiNama(String
							.valueOf(obj[index++]));
					dataPendaftaran.setLokasiKode(String.valueOf(obj[index++]));
					dataPendaftaran.setLokasiNama(String.valueOf(obj[index++]));
					dataPendaftaran
							.setJabatanKode(String.valueOf(obj[index++]));
					dataPendaftaran
							.setJabatanNama(String.valueOf(obj[index++]));
					dataPendaftaran.setPendidikanKode(String
							.valueOf(obj[index++]));
					dataPendaftaran.setPendidikanNama(String
							.valueOf(obj[index++]));
					dataPendaftaran.setTglCreated(String.valueOf(obj[index++]));
					dataPendaftaran.setStatus(String.valueOf(obj[index++]));
					//
					dataPendaftaran
							.setLokasiKode2(String.valueOf(obj[index++]));
					dataPendaftaran
							.setLokasiNama2(String.valueOf(obj[index++]));
					dataPendaftaran.setJabatanKode2(String
							.valueOf(obj[index++]));
					dataPendaftaran.setJabatanNama2(String
							.valueOf(obj[index++]));
					dataPendaftaran
							.setLokasiKode3(String.valueOf(obj[index++]));
					dataPendaftaran
							.setLokasiNama3(String.valueOf(obj[index++]));
					dataPendaftaran.setJabatanKode3(String
							.valueOf(obj[index++]));
					dataPendaftaran.setJabatanNama3(String
							.valueOf(obj[index++]));
					dataPendaftaran.setLokasiTest(String.valueOf(obj[index++]));
					dataPendaftaran.setLokasiTestNama(String
							.valueOf(obj[index++]));

					result.add(dataPendaftaran);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

}
