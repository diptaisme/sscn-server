package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.RefJabatan;

public interface JabatanService {
	List<RefJabatan> findAllJabatan(int... idx);
	
	List<RefJabatan> findJabatanByLikeName(String name) ;
}
