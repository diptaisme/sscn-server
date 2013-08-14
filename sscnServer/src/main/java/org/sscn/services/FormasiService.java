package org.sscn.services;

import org.sscn.persistence.entities.MFormasi;

public interface FormasiService {
	
	MFormasi insertFormasi(MFormasi mFormasi);
	
	MFormasi updateFormasi(MFormasi mFormasi);
	
	boolean deleteFormasi(int id);
}
