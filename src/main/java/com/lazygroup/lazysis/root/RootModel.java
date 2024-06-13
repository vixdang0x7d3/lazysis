package com.lazygroup.lazysis.root;

import org.springframework.stereotype.Component;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
public class RootModel {
	private StringProperty username = new SimpleStringProperty("");
	private StringProperty password = new SimpleStringProperty("");
	private StringProperty group = new SimpleStringProperty("");
	private StringProperty site = new SimpleStringProperty("");

	private StringProperty lop = new SimpleStringProperty("");

	private BooleanProperty loggedIn = new SimpleBooleanProperty(false);

	private StringProperty inAppName = new SimpleStringProperty("");

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

	public String getGroup() {
		return this.group.get();
	}

	public void setGroup(String group) {
		this.group.set(group);
	}

	public StringProperty groupProperty() {
		return group;
	}

	public String getSite() {
		return this.site.get();
	}

	public void setSite(String group) {
		this.site.set(group);
	}

	public StringProperty siteProperty() {
		return site;
	}

	public String getLop() {
		return this.lop.get();
	}

	public void setLop(String lop) {
		this.lop.set(lop);
	}

	public StringProperty lopProperty() {
		return lop;
	}

	public Boolean getLoggedIn() {
		return this.loggedIn.get();
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn.set(loggedIn);
	}

	public BooleanProperty loggedInProperty() {
		return loggedIn;
	}

	public String getInAppName() {
		return this.inAppName.get();
	}

	public void setInAppName(String inAppName) {
		this.inAppName.set(inAppName);
	}

	public StringProperty inAppNameProperty() {
		return inAppName;
	}

}
