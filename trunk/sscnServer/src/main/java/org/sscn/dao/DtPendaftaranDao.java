package org.sscn.dao;

import java.util.List;
import java.util.Map;

import org.sscn.core.persistence.dao.CoreDao;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.persistence.entities.RefJabatan;
import org.sscn.persistence.entities.view.DataPendaftaran;
import org.sscn.persistence.entities.view.RekapanPendaftaran;
import org.sscn.persistence.entities.view.RekapanPendaftaranTidakLulus;
import org.sscn.persistence.entities.view.StatInstansi;
import org.sscn.persistence.entities.view.StatInstansiJabatan;

public interface DtPendaftaranDao extends CoreDao<DtPendaftaran> {

	String getnoUrutPendaftaran(String sebelasDigitPertama);

	List<DtPendaftaran> findByInstansi(RefInstansi instansi,
			final int... idxAndCount);
	
	List<DtPendaftaran> findByInstansiAndMap(RefInstansi instansi, Map<String, Object> map,
			final int... idxAndCount);
	
	List<DtPendaftaran> findByInstansiAndMapFilterVerify(RefInstansi instansi, Map<String, Object> map,
			final int... idxAndCount);

	Integer countByInstansi(RefInstansi refInstansi);
	
	Integer countByInstansiAndMap(RefInstansi refInstansi, Map<String, Object> map);
	
	Integer countByInstansiAndMapFilterVerify(RefInstansi refInstansi, Map<String, Object> map);

	List<DataPendaftaran> findDataPendaftaran(String kodeInstansi);
	
	List<DataPendaftaran> findDataPesertaTest(String kodeInstansi);
	
	List<StatInstansi> getStatistikPendaftaranInstansi();
	
	List<StatInstansiJabatan> getStatistikJabatanPendaftaranInstansi(String kodeInstansi) ;
	
	List<StatInstansi> getStatistikPendaftaranInstansi(
			String kodeInstansi) ;
	
	List<RekapanPendaftaran> getRekapanPendaftaranInstansi(
			String kodeInstansi) ;

	
	List<DtPendaftaran> findReverifikasiByInstansi(RefInstansi instansi,
			int... idxAndCount);
	
	Integer countReverifikasiByInstansi(RefInstansi refInstansi);
	
	List<DtPendaftaran> findReverifikasiByInstansiAndMap(RefInstansi instansi, Map<String, Object> map,
			final int... idxAndCount);
	
	Integer countReverifikasiByInstansiAndMap(RefInstansi refInstansi, Map<String, Object> map);

	List<RekapanPendaftaranTidakLulus> getRekapanPendaftaranTidakLulusInstansi(
			String kodeInstansi);
}
