package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.DtUser;

public interface UserService {
	
	List<DtUser> getAllUser(int... idx);

	boolean editUser(DtUser user, String kodeInstansi);
	
	boolean addUser(DtUser user, String kodeInstansi);
	
	boolean deleteUserByUsername(String username);
	
	//
	DtUser insertUser(DtUser user);
	
	List<DtUser> findByProperty(String name, String value, int...idx);
	
	boolean changePassword(DtUser user, String password);
	
	boolean isSamePassword(String password, String currentPassword);
}
