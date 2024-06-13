package com.lazygroup.lazysis.login;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.util.DatabaseUtils;
import com.lazygroup.lazysis.exception.InvalidCredentialException;

import javafx.beans.binding.Bindings;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginInteractor {

	private final LoginModel model;
	private final DatabaseUtils dbUtils;

	public LoginInteractor(LoginModel model, DatabaseUtils dbUtils) {
		this.dbUtils = dbUtils;
		this.model = model;
		model.okToLoginProperty().bind(Bindings.createBooleanBinding(this::isDataValid,
				model.usernameProperty(),
				model.passwordProperty(),
				model.siteProperty()));

	}

	private Boolean isDataValid() {
		return !model.getUsername().isEmpty() &&
				!model.getPassword().isEmpty() &&
				!model.getSite().isEmpty();
	}

	public LoginDto login() throws InvalidCredentialException {

		log.info("Login with "
				+ model.getUsername() + ", "
				+ model.getPassword() + ", "
				+ model.getSite() + ", "
				+ model.getIsSvLogin());

		String username = model.getUsername();
		String password = model.getPassword();
		String site = model.getSite();
		Boolean isSv = model.getIsSvLogin();

		Optional<LoginDto> loginOptional = dbUtils.validateLogin(username, password, site, isSv);

		if (loginOptional.isPresent()) {
			return loginOptional.get();
		} else {
			throw new InvalidCredentialException("Login failed");
		}
	}

	public void resetModel() {
		model.setSite("");
		model.setUsername("");
		model.setPassword("");
		model.setIsSvLogin(false);
		model.setOkToLogin(false);
	}
}
