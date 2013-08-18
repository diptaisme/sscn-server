package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.DtPersyaratan;
import org.sscn.persistence.entities.DtVerifikasiNok;
import org.sscn.persistence.entities.RefInstansi;

/**
 * @author efraim
 * 
 */
public interface VerfikasiService {
	
	boolean simpanHasilVerifikasi(DtPendaftaran pendaftaran, List<DtVerifikasiNok> verNok);
}
