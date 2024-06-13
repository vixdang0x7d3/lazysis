package com.lazygroup.lazysis.home;

import org.springframework.stereotype.Component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
public class HomeModel {
	private StringProperty inAppName = new SimpleStringProperty("");
	private StringProperty maGv_maSv = new SimpleStringProperty("");
	private StringProperty group = new SimpleStringProperty("");
	private StringProperty maLop = new SimpleStringProperty("");

	public String getInAppName() {
		return this.inAppName.get();
	}

	public void setInAppName(String inAppName) {
		this.inAppName.set(inAppName);
	}

	public StringProperty inAppNameProperty() {
		return inAppName;
	}

	public String getMaGv_MaSv() {
		return this.maGv_maSv.get();
	}

	public void setMaGv_MaSv(String maGv_maSv) {
		this.maGv_maSv.set(maGv_maSv);
	}

	public StringProperty maGv_maSvProperty() {
		return maGv_maSv;
	}

	public String getGroup() {
		return this.group.get();
	}

	public void getGroup(String group) {
		this.group.set(group);
	}

	public StringProperty groupProperty() {
		return group;
	}

	public String getmaLop() {
		return this.maLop.get();
	}

	public void getmaLop(String maLop) {
		this.maLop.set(maLop);
	}

	public StringProperty maLopProperty() {
		return maLop;
	}

}
