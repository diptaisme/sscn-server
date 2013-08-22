package org.sscn.services;

import java.util.List;

import org.sscn.core.persistence.tools.QueryOrder;
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
	
	List<DtPersyaratan> findByProperty(String name, Object value,List<QueryOrder> orders, int...idx);
	
	boolean delete(DtPersyaratan persyaratan);
}
