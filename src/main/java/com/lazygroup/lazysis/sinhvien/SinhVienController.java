package com.lazygroup.lazysis.sinhvien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

@Component
public class SinhVienController {

	private final ObservableList<SinhVienModel> model;
	private final SinhVienViewBuilder viewBuilder;

	@Autowired
	SinhVienController(ObservableList<SinhVienModel> dsSinhVienModel) {
		this.model = dsSinhVienModel;
		viewBuilder = new SinhVienViewBuilder();
	}

	public Region getView() {
		return viewBuilder.build();
	}
}
