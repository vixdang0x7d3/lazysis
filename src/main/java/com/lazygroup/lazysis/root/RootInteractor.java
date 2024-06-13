package com.lazygroup.lazysis.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.login.LoginDto;
import com.lazygroup.lazysis.util.DatabaseUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RootInteractor {

	private final DatabaseUtils dbUtils;
	private final RootModel model;

	@Autowired
	RootInteractor(RootModel model, DatabaseUtils dbUtils) {
		this.model = model;
		this.dbUtils = dbUtils;

		model.siteProperty().addListener((ob) -> {
			System.out.println("group property invalidated!, value = " + model.getSite());
		});
	}

	// called before root view is showed
	public void updateModelPostLogin(LoginDto login) {
		model.setGroup(login.getIsSv() ? "SV" : login.getNhom_lop());
		model.setLop(login.getIsSv() ? login.getNhom_lop() : "");
		model.setInAppName(login.getHoTen());
		model.setLoggedIn(true);
	}

	public void logout() {
		log.info("Logout");
		model.setLoggedIn(false);
	}
}
