package org.sscn.dao;

import java.util.List;
import java.util.Map;

import org.sscn.core.persistence.dao.CoreDao;
import org.sscn.persistence.entities.DtHasil;
import org.sscn.persistence.entities.DtPendaftaran;
import org.sscn.persistence.entities.DtPeserta;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.persistence.entities.UserPesertaTemp;
import org.sscn.persistence.entities.view.DataPendaftaran;
import org.sscn.persistence.entities.view.RekapanPendaftaran;
import org.sscn.persistence.entities.view.StatInstansi;
import org.sscn.persistence.entities.view.StatInstansiJabatan;

public interface UserPesertaTempDao extends CoreDao<UserPesertaTemp> {

}
