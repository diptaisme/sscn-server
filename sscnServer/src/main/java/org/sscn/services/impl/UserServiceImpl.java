package org.sscn.services.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.core.persistence.tools.QueryOrder;
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
	public List<DtUser> getAllUser(String order, int... idx) {
		List<QueryOrder> orders = new ArrayList<QueryOrder>();
		if (order != null){
			orders.add(new QueryOrder(order));
		}
		orders.add(new QueryOrder("refInstansi.kode"));
		return dtUserDao.findAll(null, orders, idx);
		// return dtUserDao.findAll(idx);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<DtUser> getUserByNip(String nip, int... idx) {
		List<QueryOrder> orders = new ArrayList<QueryOrder>();
		orders.add(new QueryOrder("refInstansi.kode"));
		
		return dtUserDao.findByProperty("nip", nip, orders, idx);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean editUser(DtUser user, String kodeInstansi) {
		try {
			// user.setPassword(encryptPassword(user.getPassword()));
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
			ex.printStackTrace();
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

	@Override
	@Transactional(readOnly = false)
	public boolean changePassword(DtUser user, String password) {
		try {
			user.setPassword(encryptPassword(password));
			dtUserDao.update(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public boolean isSamePassword(String password, String currentPassword) {
		PasswordUtil passwordUtil = new PasswordUtil();
		try {
			return passwordUtil.isPasswordEqual(password, currentPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<DtUser> getAllUserByInstansi(String kodeInstansi, String order, int... idx) {
		if (order != null){
			return dtUserDao.findByInstansi(kodeInstansi, order, idx);
		}
		return dtUserDao.findByInstansi(kodeInstansi, null, idx);
	}

	@Override
	public Integer countAllUser() {
		// TODO Auto-generated method stub
		return dtUserDao.countAll();
	}

	@Override
	public Integer countUserByNip(String nip) {
		// TODO Auto-generated method stub
		return dtUserDao.countByProperty("nip", nip);
	}

	@Override
	public Integer countAllUserByInstansi(String kodeInstansi) {
		// TODO Auto-generated method stub
		// return dtUserDao.countByProperty("refInstansi.kode", kodeInstansi);
		return dtUserDao.countByInstansi(kodeInstansi);
	}

	@Override
	public List<DtUser> getUserByInstansiAndNip(String kdInstansi, String nip, int... idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countUserByInstansiAndNip(String kdInstansi, String nip) {
		// TODO Auto-generated method stub
		return null;
	}

}
