package com.lazygroup.lazysis.dangky;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DangKyModelItem {

	private IntegerProperty maLtc = new SimpleIntegerProperty(0);
	private StringProperty maSv = new SimpleStringProperty("");
	private IntegerProperty diemCc = new SimpleIntegerProperty(0);
	private FloatProperty diemGk = new SimpleFloatProperty(0.0f);
	private FloatProperty diemCk = new SimpleFloatProperty(0.0f);
	private BooleanProperty huyDangKy = new SimpleBooleanProperty(false);

	public int getMaLtc() {
		return this.maLtc.get();
	}

	public void setMaLtc(int maLtc) {
		this.maLtc.set(maLtc);
	}

	public IntegerProperty maLtcProperty() {
		return maLtc;
	}

	public String getMaSv() {
		return this.maSv.get();
	}

	public void setMaSv(String maSv) {
		this.maSv.set(maSv);
	}

	public StringProperty maSvProperty() {
		return maSv;
	}

	public int getDiemCc() {
		return this.diemCc.get();
	}

	public void setDiemCc(int diemCc) {
		this.diemCc.set(diemCc);
	}

	public IntegerProperty diemCcProperty() {
		return diemCc;
	}

	public float getDiemGk() {
		return this.diemGk.get();
	}

	public void setDiemGk(float diemGk) {
		this.diemGk.set(diemGk);
	}

	public FloatProperty diemGkProperty() {
		return diemGk;
	}

	public float getDiemCk() {
		return this.diemCk.get();
	}

	public void setDiemCk(float diemCk) {
		this.diemCk.set(diemCk);
	}

	public FloatProperty diemCkProperty() {
		return diemCk;
	}

	public boolean getHuyDangKy() {
		return this.huyDangKy.get();
	}

	public void setHuyDangKy(boolean huyDangKy) {
		this.huyDangKy.set(huyDangKy);
	}

	public BooleanProperty huyDangKyProperty() {
		return huyDangKy;
	}
}
