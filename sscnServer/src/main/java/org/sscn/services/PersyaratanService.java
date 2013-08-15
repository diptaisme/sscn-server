package org.sscn.services;

import org.sscn.persistence.entities.DtPersyaratan;

/**
 * @author efraim
 * 
 */
public interface PersyaratanService {
	/**
	 * @param persyaratan
	 * @return DtPersyaratan
	 */
	DtPersyaratan simpanPersyaratan(DtPersyaratan persyaratan);
	
	boolean delete(DtPersyaratan persyaratan);
}
