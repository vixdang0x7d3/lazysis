package com.lazygroup.lazysis.sinhvien;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.dao.Dao;

import javafx.collections.ObservableList;

@Component
public class SinhVienInteractor {

	ObservableList<SinhVienModel> model;
	Dao<SinhVien> dao;

	@Autowired
	SinhVienInteractor(ObservableList<SinhVienModel> dsSinhVienModel, SinhVienDaoImpl sinhvienDao) {
		this.model = dsSinhVienModel;
		this.dao = sinhvienDao;
	}

	public List<SinhVienModel> fetchData() {

		List<SinhVienModel> data = dao.list().stream().map((sv) -> createModelItemFromSinhVien(sv))
				.collect(Collectors.toList());

		return data;
	}

	public void updateModel(List<SinhVienModel> data) {
		this.model.setAll(data);
	}

	public SinhVienModel createModelItemFromSinhVien(SinhVien sinhvien) {
		SinhVienModel modelItem = new SinhVienModel();

		modelItem.setMaSv(sinhvien.getMaSv());
		modelItem.setHo(sinhvien.getHo());
		modelItem.setTen(sinhvien.getTen());
		modelItem.setDiaChi(sinhvien.getDiaChi());
		modelItem.setIsFemale(sinhvien.getIsFemale());
		modelItem.setDiaChi(sinhvien.getDiaChi());
		modelItem.setNgaySinh(sinhvien.getNgaySinh());
		modelItem.setMaLop(sinhvien.getMaLop());
		modelItem.setDaNghiHoc(sinhvien.getDaNghiHoc());
		modelItem.setPassword(sinhvien.getPassword());

		return modelItem;
	}
}
