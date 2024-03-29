package org.sscn.services;

import java.util.List;

import org.sscn.persistence.entities.DtUser;

public interface UserService {
	
	List<DtUser> getAllUser(String order, int... idx);
	
	Integer countAllUser();
	
	List<DtUser> getAllUserByInstansi(String kodeInstansi, String order, int... idx);
	
	Integer countAllUserByInstansi(String kodeInstansi);

	boolean editUser(DtUser user, String kodeInstansi);
	
	boolean addUser(DtUser user, String kodeInstansi);
	
	boolean deleteUserByUsername(String username);
	
	//
	DtUser insertUser(DtUser user);
	
	List<DtUser> findByProperty(String name, String value, int...idx);
	
	boolean changePassword(DtUser user, String password);
	
	boolean isSamePassword(String password, String currentPassword);
	
	public List<DtUser> getUserByNip(String nip, int... idx);
	
	public Integer countUserByNip(String nip);
	
	public List<DtUser> getUserByInstansiAndNip(String kdInstansi, String nip, int... idx);
	
	public Integer countUserByInstansiAndNip(String kdInstansi, String nip);
}
