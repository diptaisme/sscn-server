package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.RefInstansi;

public interface InstansiService {
	List<RefInstansi> findAllInstansi(int... idx);

	List<RefInstansi> findInstansiByLikeName(String name);
}
