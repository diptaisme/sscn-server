package org.sscn.services.impl;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.codec.DecoderException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.sscn.core.util.PasswordUtil;
import org.sscn.dao.DtUserDao;
import org.sscn.dao.RefInstansiDao;
import org.sscn.persistence.entities.DtUser;
import org.sscn.persistence.entities.RefInstansi;
import org.sscn.services.AuthenticateService;

@Service("AuthenticateService")
public class AuthenticateServiceImpl implements AuthenticateService {

	@Inject
	private DtUserDao dtUserDao;
	@Inject
	private RefInstansiDao refInstansiDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public DtUser login(String username, String password) {
		// TODO Auto-generated method stub
		DtUser result = new DtUser();
		Boolean isValid = false;
		if (username == null || password == null) {
			throw new IllegalArgumentException("Gagal Login");
		}
		List<DtUser> listUsers = dtUserDao.findByProperty("username", username,
				null);
		if (listUsers.isEmpty()) {
			throw new IllegalArgumentException("User tidak ditemukan");
		}
		result = listUsers.get(0);

		PasswordUtil passwordUtil = new PasswordUtil();
		try {
			isValid = passwordUtil.isPasswordEqual(password,
					result.getPassword());
		} catch (IOException e) {
			System.out.println("IO exception " + e.getMessage());
			return null;
		} catch (DecoderException e) {
			System.out.println("Decoder exception = " + e.getMessage());
			return null;
		} catch (Exception e) {
			System.out.println("Exception = " + e.getMessage());
			return null;
		}

		if (!isValid) {
			return null;
		} else {
			RefInstansi instansi = refInstansiDao.findById(result.getRefInstansi().getKode());
			result.setRefInstansi(instansi);
			return result;
		}

	}
}
