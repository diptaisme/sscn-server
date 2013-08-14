package org.sscn.dao;

import org.sscn.core.persistence.dao.CoreDao;
import org.sscn.persistence.entities.DtPengumuman;

public interface DtPengumumanDao extends CoreDao<DtPengumuman> {
	void updatePengumuman(DtPengumuman dtPengumuman);
}
