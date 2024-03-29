package org.sscn.services.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.dao.DtPendaftaranDao;
import org.sscn.dao.DtVerifikasiNokDao;
import org.sscn.dao.DtVerifikasiUlangDao;
import org.sscn.dao.RefPendidikanDao;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.DtVerifikasiNok;
import org.sscn.persistence.entities.DtVerifikasiUlang;
import org.sscn.persistence.entities.RefJenisFormasi;
import org.sscn.persistence.entities.RefPendidikan;
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

	@Inject
	private RefPendidikanDao refPendidikanDao;
	
	@Inject
	private DtVerifikasiUlangDao verUlangDao;

	@Override
	@Transactional(readOnly = false)
	public boolean simpanHasilVerifikasi(DtPendaftaran pendaftaran,
			List<DtVerifikasiNok> verNoks) {
		try {
			int i = 0;
			if (pendaftaran.getFormasi() != null){
				if (pendaftaran.getFlagFormasi() == 1){
					i++;
				} 
			}
			
			if (pendaftaran.getFormasi2() != null){
				if (pendaftaran.getFlagFormasi2() == 1){
					i++;
				} 
			}
			
			if (pendaftaran.getFormasi3() != null){
				if (pendaftaran.getFlagFormasi3() == 1){
					i++;
				} 
			}
			
			
			if (i==0) {
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
	@Transactional(readOnly = false)
	public DtPendaftaran update(DtPendaftaran dtPendaftar) {
		DtPendaftaran pendaftar = null;
		try {
			pendaftar = dtPendaftaranDao.update(dtPendaftar);
		} catch (Exception e) {
			e.printStackTrace();
			pendaftar = null;
		}
		return pendaftar;
	}

	@Override
	public List<DtVerifikasiNok> findVerifikasiNoksByPendaftar(
			DtPendaftaran pendaftar) {
		return dtVerifikasiNokDao.findByProperty("pendaftar", pendaftar, null);
	}

	@Override
	public String getNoUrutPeserta(DtPendaftaran pendaftar) {

		String instansi = pendaftar.getFormasi().getRefInstansi().getKode();
		RefPendidikan pend = refPendidikanDao.findById(pendaftar
				.getPendidikan());
		String pendidikan = pend.getTingkat();

		String stringDigit = "";
		RefJenisFormasi jenisFormasi = pendaftar.getFormasi().getRefJenisFormasi();
		
		stringDigit = instansi + pendidikan.substring(0, 1) + '0';
		String nourut = dtPendaftaranDao.getnoUrutPendaftaran(stringDigit);
		if (nourut.contentEquals("")) {
			if (jenisFormasi.getNama().equalsIgnoreCase("UMUM")){
				nourut = "00001";				
			} else {
				nourut = jenisFormasi.getPrefixCode() + "001";
			}						
		} else {
			int x = Integer.parseInt(nourut) + 1;
			nourut = String.format("%05d", x);
		}

	//	Integer varMod = 9 - (Integer.parseInt(nourut) % 8);
		return stringDigit + nourut;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean verifikasiUlang(DtVerifikasiUlang reVer, DtPendaftaran pendaftaran) {
		try {
			List<DtVerifikasiNok> verNok = dtVerifikasiNokDao.findByProperty("pendaftar", pendaftaran, null);
			if (verNok != null && verNok.size()>0){
				dtVerifikasiNokDao.removeBulk(verNok);
			}			
			verUlangDao.insert(reVer);
			dtPendaftaranDao.update(pendaftaran);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
