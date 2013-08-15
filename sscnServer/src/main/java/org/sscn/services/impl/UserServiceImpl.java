package org.sscn.services.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.core.util.PasswordUtil;
import org.sscn.dao.DtUserDao;
import org.sscn.dao.RefInstansiDao;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.services.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Inject
	private DtUserDao dtUserDao;
	@Inject
	private RefInstansiDao refInstansiDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<DtUser> getAllUser(int... idx) {
		return dtUserDao.findAll(idx);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean editUser(DtUser user, String kodeInstansi) {
		try {
			//user.setPassword(encryptPassword(user.getPassword()));
			RefInstansi instansi = refInstansiDao.findById(kodeInstansi);
			user.setRefInstansi(instansi);
			dtUserDao.update(user);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public boolean addUser(DtUser user, String kodeInstansi) {
		try {
			user.setPassword(encryptPassword(user.getPassword()));
			RefInstansi instansi = refInstansiDao.findById(kodeInstansi);
			user.setRefInstansi(instansi);
			dtUserDao.insert(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public boolean deleteUserByUsername(String username) {
		try {
			DtUser user = dtUserDao.findById(username);
			dtUserDao.remove(user);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	private String encryptPassword(String password) {
		String encryptedPassword = null;
		try {
			PasswordUtil passwordUtil = new PasswordUtil();
			encryptedPassword = passwordUtil.encryptPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return encryptedPassword;
	}

	//
	@Override
	@Transactional(readOnly = false)
	public DtUser insertUser(DtUser user) {
		return dtUserDao.insert(user);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<DtUser> findByProperty(String name, String value, int... idx) {		
		return dtUserDao.findByProperty(name, value, idx);
	}

}
