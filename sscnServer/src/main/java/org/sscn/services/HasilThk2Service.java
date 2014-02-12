package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.HasilThk2;
import org.sscn.persistence.entities.InstansiThk2;
import org.sscn.persistence.entities.PropThk2;
import org.sscn.persistence.entities.RefJabatan;

public interface HasilThk2Service {
	List<PropThk2> findProvinsiByJenis(String jenis);
	List<InstansiThk2> findInstansiKerjaThk2ByProvinsi(String provinsi);
	List<HasilThk2> findHasilThk2ByInstansi(String instansi, String order, final int... rowStartIdxAndCount);
	Integer countHasilThk2ByInstansi(String instansi);
	List<HasilThk2> findHasilThk2ByNoTest(String noTest);
	List<HasilThk2> findHasilThk2ByInstansiNoTest(String noTest, String instansiId);
}
