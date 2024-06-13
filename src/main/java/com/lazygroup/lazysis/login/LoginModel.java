package com.lazygroup.lazysis.login;

import org.springframework.stereotype.Component;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
public class LoginModel {

	private final StringProperty site = new SimpleStringProperty("");
	private final StringProperty username = new SimpleStringProperty("");
	private final StringProperty password = new SimpleStringProperty("");
	private final BooleanProperty isSvLogin = new SimpleBooleanProperty(false);
	private final BooleanProperty okToLogin = new SimpleBooleanProperty(false);

	public String getSite() {
		return this.site.get();
	}

	public void setSite(String site) {
		this.site.set(site);
	}

	public StringProperty siteProperty() {
		return site;
	}

	public String getUsername() {
		return this.username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public StringProperty usernameProperty() {
		return username;
	}

	public String getPassword() {
		return this.password.get();
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public StringProperty passwordProperty() {
		return password;
	}

	public Boolean getIsSvLogin() {
		return this.isSvLogin.get();
	}

	public void setIsSvLogin(Boolean isSvLogin) {
		this.isSvLogin.set(isSvLogin);
	}

	public BooleanProperty isSvLoginProperty() {
		return isSvLogin;
	}

	public Boolean getOkToLogin() {
		return this.okToLogin.get();
	}

	public void setOkToLogin(Boolean okToLogin) {
		this.okToLogin.set(okToLogin);
	}

	public BooleanProperty okToLoginProperty() {
		return okToLogin;
	}
}
