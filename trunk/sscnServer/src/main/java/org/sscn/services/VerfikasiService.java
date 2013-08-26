package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.DtVerifikasiNok;

/**
 * @author efraim
 * 
 */
public interface VerfikasiService {

	boolean simpanHasilVerifikasi(DtPendaftaran pendaftaran, List<DtVerifikasiNok> verNok);

	List<DtVerifikasiNok> findVerifikasiNoksByPendaftar(DtPendaftaran pendaftarId);

	String getNoUrutPeserta(DtPendaftaran pendaftar);
}
