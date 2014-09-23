package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.view.StatInstansi;
import org.sscn.persistence.entities.view.StatInstansiJabatan;
import org.sscn.persistence.entities.view.StatInstansiJabatan3Pilihan;

public interface StatistikService {

	List<StatInstansi> getStatistikPendaftaranInstansi();

	List<StatInstansiJabatan> getStatistikJabatanPendaftaranInstansi(
			String kodeInstansi);

	List<StatInstansi> getStatistikPendaftaranInstansi(String kodeInstansi);

	List<StatInstansiJabatan3Pilihan> getStatistikJabatanPendaftaranInstansi3Pilihan(
			String kodeInstansi);
}
