package com.lazygroup.lazysis.sinhvien;

import java.time.LocalDateTime;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SinhVienModelItem {

	private StringProperty maSv = new SimpleStringProperty("");
	private StringProperty ho = new SimpleStringProperty("");
	private StringProperty ten = new SimpleStringProperty("");
	private BooleanProperty isFemale = new SimpleBooleanProperty(false);
	private StringProperty diaChi = new SimpleStringProperty("");
	private ObjectProperty<LocalDateTime> ngaySinh = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now());
	private StringProperty maLop = new SimpleStringProperty("");
	private BooleanProperty daNghiHoc = new SimpleBooleanProperty(false);
	private StringProperty password = new SimpleStringProperty("");

	public String getMaSv() {
		return this.maSv.get();
	}

	public void setMaSv(String maSv) {
		this.maSv.set(maSv);
	}

	public StringProperty maSvProperty() {
		return maSv;
	}

	public String getHo() {
		return this.ho.get();
	}

	public void setHo(String ho) {
		this.ho.set(ho);
	}

	public StringProperty hoProperty() {
		return ho;
	}

	public String getTen() {
		return this.ten.get();
	}

	public void setTen(String ten) {
		this.ten.set(ten);
	}

	public StringProperty tenProperty() {
		return ten;
	}

	public Boolean getIsFemale() {
		return this.isFemale.get();
	}

	public void setIsFemale(Boolean isFemale) {
		this.isFemale.set(isFemale);
	}

	public BooleanProperty isFemaleProperty() {
		return isFemale;
	}

	public String getDiaChi() {
		return this.diaChi.get();
	}

	public void setDiaChi(String diaChi) {
		this.diaChi.set(diaChi);
	}

	public StringProperty diaChiProperty() {
		return diaChi;
	}

	public LocalDateTime getNgaySinh() {
		return this.ngaySinh.get();
	}

	public void setNgaySinh(LocalDateTime ngaySinh) {
		this.ngaySinh.set(ngaySinh);
	}

	public ObjectProperty<LocalDateTime> ngaySinhProperty() {
		return ngaySinh;
	}

	public String getMaLop() {
		return this.maLop.get();
	}

	public void setMaLop(String maLop) {
		this.maLop.set(maLop);
	}

	public StringProperty maLopProperty() {
		return maLop;
	}

	public Boolean getDaNghiHoc() {
		return this.daNghiHoc.get();
	}

	public void setDaNghiHoc(Boolean daNghiHoc) {
		this.daNghiHoc.set(daNghiHoc);
	}

	public BooleanProperty daNghiHocProperty() {
		return daNghiHoc;
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
}
