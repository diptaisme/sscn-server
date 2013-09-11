package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.view.StatInstansi;
import org.sscn.persistence.entities.view.StatInstansiJabatan;

public interface StatistikService {

	List<StatInstansi> getStatistikPendaftaranInstansi();
	
	List<StatInstansiJabatan> getStatistikJabatanPendaftaranInstansi(String kodeInstansi);
}
