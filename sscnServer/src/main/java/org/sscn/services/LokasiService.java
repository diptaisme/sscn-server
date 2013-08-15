package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.RefLokasi;

public interface LokasiService {

	List<RefLokasi> findAllLokasi(int... idx);

	List<RefLokasi> findLokasiByLikeName(String name);

	RefLokasi save(String kode, String name, String instansiKd);

	RefLokasi update(String kode, String name);
	
	RefLokasi delete(String kode);
	
	RefLokasi findLokasiById(String kode);
}
