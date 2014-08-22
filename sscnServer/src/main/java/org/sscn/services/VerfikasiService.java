package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.DtVerifikasiNok;
import org.sscn.persistence.entities.DtVerifikasiUlang;

/**
 * @author efraim
 * 
 */
public interface VerfikasiService {

	boolean simpanHasilVerifikasi(DtPendaftaran pendaftaran, List<DtVerifikasiNok> verNok);
	
	DtPendaftaran update(DtPendaftaran dtPendaftar);

	List<DtVerifikasiNok> findVerifikasiNoksByPendaftar(DtPendaftaran pendaftarId);

	String getNoUrutPeserta(DtPendaftaran pendaftar);

	boolean verifikasiUlang(DtVerifikasiUlang reVer, DtPendaftaran pendaftaran);
}
