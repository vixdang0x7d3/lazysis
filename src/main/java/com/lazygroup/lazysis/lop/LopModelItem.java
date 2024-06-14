package com.lazygroup.lazysis.lop;

import org.springframework.stereotype.Component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
public class LopModelItem {
	private StringProperty maLop = new SimpleStringProperty("");
	private StringProperty tenLop = new SimpleStringProperty("");
	private StringProperty khoaHoc = new SimpleStringProperty("");
	private StringProperty maKhoa = new SimpleStringProperty("");

	public String getMaLop() {
		return this.maLop.get();
	}

	public void setMaLop(String maLop) {
		this.maLop.set(maLop);
	}

	public StringProperty maLopProperty() {
		return maLop;
	}

	public String getTenLop() {
		return this.tenLop.get();
	}

	public void setTenLop(String tenLop) {
		this.tenLop.set(tenLop);
	}

	public StringProperty tenLopProperty() {
		return tenLop;
	}

	public String getKhoaHoc() {
		return this.khoaHoc.get();
	}

	public void setKhoaHoc(String khoaHoc) {
		this.khoaHoc.set(khoaHoc);
	}

	public StringProperty khoaHocProperty() {
		return khoaHoc;
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
}
