package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.RefPendidikan;

public interface PendidikanService {

	List<RefPendidikan> findAllPendidikan(int... idx);

	List<RefPendidikan> findPendidikanByLikeName(String name);

}
