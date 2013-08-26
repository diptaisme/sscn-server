package org.sscn.services.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.dao.DtVerifikasiNokDao;
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
				System.out.println("kaditu");
			} else {
				System.out.println("kadiye");
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
	public String getNoUrutPeserta(String stringDigit) {
		return dtPendaftaranDao.getnoUrutPendaftaran(stringDigit);
	}

}
