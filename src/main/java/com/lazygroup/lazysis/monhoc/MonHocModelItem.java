package com.lazygroup.lazysis.monhoc;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MonHocModelItem {

	private StringProperty maMh = new SimpleStringProperty("");
	private StringProperty tenMh = new SimpleStringProperty("");
	private IntegerProperty soTietLt = new SimpleIntegerProperty(0);
	private IntegerProperty soTietTh = new SimpleIntegerProperty(0);

	public String getMaMh() {
		return this.maMh.get();
	}

	public void setMaMh(String maMh) {
		this.maMh.set(maMh);
	}

	public StringProperty maMhProperty() {
		return maMh;
	}

	public String getTenMh() {
		return this.tenMh.get();
	}

	public void setTenMh(String tenMh) {
		this.tenMh.set(tenMh);
	}

	public StringProperty tenMhProperty() {
		return tenMh;
	}

	public int getSoTietLt() {
		return this.soTietLt.get();
	}

	public void setSoTietLt(int soTietLt) {
		this.soTietLt.set(soTietLt);
	}

	public IntegerProperty soTietLtProperty() {
		return soTietLt;
	}

	public int getSoTietTh() {
		return this.soTietTh.get();
	}

	public void setSoTietTh(int soTietTh) {
		this.soTietTh.set(soTietTh);
	}

	public IntegerProperty soTietThProperty() {
		return soTietTh;
	}
}
