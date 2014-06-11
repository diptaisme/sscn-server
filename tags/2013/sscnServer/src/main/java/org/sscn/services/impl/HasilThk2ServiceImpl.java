package org.sscn.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.core.persistence.tools.QueryOrder;
import org.sscn.dao.DtHasilDao;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.dao.DtPesertaDao;
import org.sscn.dao.HasilThk2Dao;
import org.sscn.dao.InstansiThk2Dao;
import org.sscn.dao.MFormasiDao;
import org.sscn.dao.PropThk2Dao;
import org.sscn.dao.RefInstansiDao;
import org.sscn.dao.RefLokasiDao;
import org.sscn.dao.RefPendidikanDao;
import org.sscn.persistence.entities.DtFormasi;
import org.sscn.persistence.entities.DtHasil;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.DtPeserta;
import org.sscn.persistence.entities.HasilThk2;
import org.sscn.persistence.entities.InstansiThk2;
import org.sscn.persistence.entities.MFormasi;
import org.sscn.persistence.entities.PropThk2;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.persistence.entities.RefJabatan;
import org.sscn.persistence.entities.RefLokasi;
import org.sscn.persistence.entities.RefPendidikan;
import org.sscn.services.HasilService;
import org.sscn.services.HasilThk2Service;
import org.sscn.services.RegistrasiService;

@Service("HasilThk2Service")
public class HasilThk2ServiceImpl implements HasilThk2Service {
	@Inject
	private PropThk2Dao propThk2Dao;
	
	@Inject
	private InstansiThk2Dao instansiThk2Dao;
	
	@Inject
	private HasilThk2Dao hasilThk2Dao;
	
	@Inject
	private RefPendidikanDao refPendidikanDao;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<PropThk2> findProvinsiByJenis(String jenis) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("jenis", jenis);

		return propThk2Dao.findByMapOfProperties(map, null, null);	
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<InstansiThk2> findInstansiKerjaThk2ByProvinsi(String provinsi) {

		Map<String, Object> map = new HashMap<String, Object>();
		PropThk2 prop = propThk2Dao.findById(provinsi);
		map.put("prop", prop);
		map.put("flag", "1");

		List<QueryOrder> listOrder = new ArrayList<QueryOrder>();
		listOrder.add(new QueryOrder("kode"));
		return instansiThk2Dao.findByMapOfProperties(map, listOrder, null);	
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<HasilThk2> findHasilThk2ByInstansi(String instansi, String order, final int... rowStartIdxAndCount) {

		Map<String, Object> map = new HashMap<String, Object>();
		InstansiThk2 instansiKerja = instansiThk2Dao.findById(instansi);
		map.put("instansiThk2", instansiKerja);
		
		List<QueryOrder> listOrder = new ArrayList<QueryOrder>();
		listOrder.add(new QueryOrder(order));
		return hasilThk2Dao.findByMapOfProperties(map, listOrder, rowStartIdxAndCount);	
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<HasilThk2> findHasilThk2ByNoTest(String noTest) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("noTest", noTest);
		
		return hasilThk2Dao.findByMapOfProperties(map, null, null);	
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<HasilThk2> findHasilThk2ByInstansiNoTest(String noTest, String instansiId) {

		InstansiThk2 instansiKerja = instansiThk2Dao.findById(instansiId);		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("noTest", noTest);
		map.put("instansiThk2", instansiKerja);
		
		return hasilThk2Dao.findByMapOfProperties(map, null, null);	
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Integer countHasilThk2ByInstansi(String instansi) {

		InstansiThk2 instansiKerja = instansiThk2Dao.findById(instansi);
		return hasilThk2Dao.countByProperty("instansiThk2", instansiKerja);	
	}
}
