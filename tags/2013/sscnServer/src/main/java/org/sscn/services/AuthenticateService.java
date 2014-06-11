package org.sscn.services;

import org.sscn.persistence.entities.DtUser;

public interface AuthenticateService {
	DtUser login(String username, String password);
}
