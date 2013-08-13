package org.sscn.util.json;

import java.util.ArrayList;
import java.util.List;

import org.sscn.model.json.UserJson;
import org.sscn.persistence.entities.DtUser;

public class ObjectConverterJson {
	public static UserJson convertFromDtUser(DtUser dtUser) {
		UserJson userJson = new UserJson();
		userJson.setKewenangan(dtUser.getKewenangan());
		userJson.setKodeInstansi(dtUser.getRefInstansi().getKode());
		userJson.setNamaInstansi(dtUser.getRefInstansi().getNama());
		userJson.setNama(dtUser.getNama());
		userJson.setNip(dtUser.getNip());
		userJson.setNipAdmin(dtUser.getNipAdmin());
		userJson.setTglCreated(dtUser.getTglCreated());
		userJson.setTglUpdated(dtUser.getTglUpdated());
		return userJson;
	}

	public static List<UserJson> convertFromListDtUser(List<DtUser> listDtUser) {
		List<UserJson> listUserJson = new ArrayList<UserJson>();
		for (DtUser dtUser : listDtUser) {
			UserJson userJson = new UserJson();
			userJson.setKewenangan(dtUser.getKewenangan());
			userJson.setKodeInstansi(dtUser.getRefInstansi().getKode());
			userJson.setNamaInstansi(dtUser.getRefInstansi().getNama());
			userJson.setNama(dtUser.getNama());
			userJson.setNip(dtUser.getNip());
			userJson.setNipAdmin(dtUser.getNipAdmin());
			userJson.setTglCreated(dtUser.getTglCreated());
			userJson.setTglUpdated(dtUser.getTglUpdated());
			listUserJson.add(userJson);
		}
		return listUserJson;
	}
}
