package org.sscn.services;

import java.util.Set;

import org.sscn.persistence.entities.DtFormasi;
import org.sscn.persistence.entities.MFormasi;

public interface FormasiService {
	
	MFormasi insertFormasi(MFormasi mFormasi);
	
	MFormasi updateFormasi(MFormasi mFormasiOld, Set<DtFormasi> mFormasi);
	
	boolean deleteFormasi(int id);
}
