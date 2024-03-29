package org.sscn.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.persistence.entities.RefJabatan;
import org.sscn.persistence.entities.RefLokasi;
import org.sscn.persistence.entities.RefPendidikan;

public interface RegistrasiService {
	DtPendaftaran insertNewRegistrasi(DtPendaftaran dtPendaftaran);

	List<RefInstansi> getInstansi(int maxRows, String startWith);

	List<RefLokasi> getLokasi(String instansi);

	List<RefJabatan> getJabatan(String instansi, String lokasi);

	List<RefPendidikan> getPendidikan(String instansi, String lokasi,
			String jabatan);

	DtPendaftaran insertPendaftaran(HttpServletRequest request);
	
	DtPendaftaran getPendaftaranByNoRegistrasi(String noRegister);
	
	List<RefPendidikan> getPendidikan(String instansi, String lokasi);
	

	List<RefJabatan> getJabatan(String instansi, String lokasi, String pendidikan);
}
