package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.DtUser;

public interface UserService {
	List<DtUser> getAllUser(int... idx);

	boolean editUser(DtUser user, String kodeInstansi);
	
	boolean addUser(DtUser user, String kodeInstansi);
	
	boolean deleteUser(String nip);
	
	//
	DtUser insertUser(DtUser user);
}
