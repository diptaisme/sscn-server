package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.DtPengumuman;
import org.sscn.persistence.entities.RefInstansi;

public interface PengumumanService {
	List<DtPengumuman> getPengumuman(int... idx);

	List<RefInstansi> getInstansi(int... idx);
}
