package com.lazygroup.lazysis.loptinchi;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LopTinChiModelItem {

	private IntegerProperty maLtc = new SimpleIntegerProperty(0);
	private StringProperty nienKhoa = new SimpleStringProperty("");
	private IntegerProperty hocKy = new SimpleIntegerProperty(0);
	private StringProperty maMh = new SimpleStringProperty("");
	private IntegerProperty nhom = new SimpleIntegerProperty(0);
	private StringProperty maGv = new SimpleStringProperty("");
	private StringProperty maKhoa = new SimpleStringProperty("");
	private IntegerProperty soSvToiThieu = new SimpleIntegerProperty(0);
	private BooleanProperty huyLop = new SimpleBooleanProperty(false);

	public int getMaLtc() {
		return this.maLtc.get();
	}

	public void setMaLtc(int maLtc) {
		this.maLtc.set(maLtc);
	}

	public IntegerProperty maLtcProperty() {
		return maLtc;
	}

	public String getNienKhoa() {
		return this.nienKhoa.get();
	}

	public void setNienKhoa(String nienKhoa) {
		this.nienKhoa.set(nienKhoa);
	}

	public StringProperty nienKhoaProperty() {
		return nienKhoa;
	}

	public int getHocKy() {
		return this.hocKy.get();
	}

	public void setHocKy(int hocKy) {
		this.hocKy.set(hocKy);
	}

	public IntegerProperty hocKyProperty() {
		return hocKy;
	}

	public String getMaMh() {
		return this.maMh.get();
	}

	public void setMaMh(String maMh) {
		this.maMh.set(maMh);
	}

	public StringProperty maMhProperty() {
		return maMh;
	}

	public int getNhom() {
		return this.nhom.get();
	}

	public void setNhom(int nhom) {
		this.nhom.set(nhom);
	}

	public IntegerProperty nhomProperty() {
		return nhom;
	}

	public String getMaGv() {
		return this.maGv.get();
	}

	public void setMaGv(String maGv) {
		this.maGv.set(maGv);
	}

	public StringProperty maGvProperty() {
		return maGv;
	}

	public String getMaKhoa() {
		return this.maKhoa.get();
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa.set(maKhoa);
	}

	public StringProperty maKhoaProperty() {
		return maKhoa;
	}

	public int getSoSvToiThieu() {
		return this.soSvToiThieu.get();
	}

	public void setSoSvToiThieu(int soSvToiThieu) {
		this.soSvToiThieu.set(soSvToiThieu);
	}

	public IntegerProperty soSvToiThieuProperty() {
		return soSvToiThieu;
	}

	public boolean getHuyLop() {
		return this.huyLop.get();
	}

	public void setHuyLop(boolean huyLop) {
		this.huyLop.set(huyLop);
	}

	public BooleanProperty huyLopProperty() {
		return huyLop;
	}
}
