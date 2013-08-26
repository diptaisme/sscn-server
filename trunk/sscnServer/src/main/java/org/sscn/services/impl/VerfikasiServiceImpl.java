package org.sscn.services.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.dao.DtVerifikasiNokDao;
import org.sscn.persistence.entities.DtFormasi;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.DtVerifikasiNok;
import org.sscn.services.VerfikasiService;

/**
 * @author efraim
 * 
 */
@Service(value = "verifikasiService")
public class VerfikasiServiceImpl implements VerfikasiService {

	@Inject
	private DtPendaftaranDao dtPendaftaranDao;

	@Inject
	private DtVerifikasiNokDao dtVerifikasiNokDao;

	@Override
	@Transactional(readOnly = false)
	public boolean simpanHasilVerifikasi(DtPendaftaran pendaftaran,
	        List<DtVerifikasiNok> verNoks) {
		try {
			if (verNoks.size() > 0) {
				// jika gagal
				pendaftaran.setStatus("0");
				pendaftaran.setTglValidate(new Date());
				dtPendaftaranDao.update(pendaftaran);
				Iterator<DtVerifikasiNok> iterator = verNoks.iterator();
				while (iterator.hasNext()) {
					DtVerifikasiNok dtVer = iterator.next();
					dtVerifikasiNokDao.insert(dtVer);
				}
			} else {
				String noPeserta = getNoUrutPeserta(pendaftaran);
				pendaftaran.setNoPeserta(noPeserta);
				pendaftaran.setStatus("1");
				pendaftaran.setTglValidate(new Date());
				dtPendaftaranDao.update(pendaftaran);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<DtVerifikasiNok> findVerifikasiNoksByPendaftar(DtPendaftaran pendaftar) {
		return dtVerifikasiNokDao.findByProperty("pendaftar", pendaftar, null);
	}

	@Override
	public String getNoUrutPeserta(DtPendaftaran pendaftar) {
		
		String instansi = pendaftar.getFormasi().getRefInstansi().getKode();
		Set<DtFormasi> dtForms = pendaftar.getFormasi().getDtFormasis();
		DtFormasi forms = dtForms.iterator().next();
		String pendidikan = forms.getPendidikan().getKode();
		String jabatan = pendaftar.getFormasi().getRefJabatan().getKode();
		String stringDigit = instansi+pendidikan+jabatan;
		String nourut = dtPendaftaranDao.getnoUrutPendaftaran(stringDigit);
		if (nourut.contentEquals("")){
			nourut = "0";
		}
		return stringDigit+nourut;
	}

}
