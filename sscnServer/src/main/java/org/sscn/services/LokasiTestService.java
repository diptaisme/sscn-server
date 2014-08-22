package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.RefLokasi;
import org.sscn.persistence.entities.RefLokasiTest;

public interface LokasiTestService {

	List<RefLokasiTest> findAllLokasi(int... idx);
	
	Integer countFindAllLokasiByInstansi(String kodeInstansi);
	
	List<RefLokasiTest> findAllLokasiByInstansi(String kodeInstansi, int... idx);

	List<RefLokasiTest> findLokasiByLikeName(String name);
	
	List<RefLokasiTest> findLokasiByLikeNameInstansi(String name, String instansiKd);

	RefLokasiTest save(String kode, String name, String instansiKd);

	RefLokasiTest update(String kode, String name);
	
	RefLokasiTest delete(String kode);
	
	RefLokasiTest findLokasiById(String kode);
}
