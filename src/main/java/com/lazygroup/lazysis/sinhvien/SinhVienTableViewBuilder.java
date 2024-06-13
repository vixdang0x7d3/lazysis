package com.lazygroup.lazysis.sinhvien;

import com.lazygroup.lazysis.util.DateColumn;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class SinhVienTableViewBuilder implements Builder<Region> {

	private final ObservableList<SinhVienModel> model;

	public SinhVienTableViewBuilder(ObservableList<SinhVienModel> model) {
		this.model = model;
	}

	@Override
	public Region build() {
		TableView<SinhVienModel> results = new TableView<>();

		TableColumn<SinhVienModel, String> maSvCol = new TableColumn<>("MASV");
		maSvCol.setCellValueFactory(cdf -> cdf.getValue().maSvProperty());
		results.getColumns().add(maSvCol);

		TableColumn<SinhVienModel, String> hoCol = new TableColumn<>("HO");
		maSvCol.setCellValueFactory(cdf -> cdf.getValue().hoProperty());
		results.getColumns().add(hoCol);

		TableColumn<SinhVienModel, String> tenCol = new TableColumn<>("TEN");
		maSvCol.setCellValueFactory(cdf -> cdf.getValue().tenProperty());
		results.getColumns().add(tenCol);

		TableColumn<SinhVienModel, String> phaiCol = new TableColumn<>("PHAI");
		maSvCol.setCellValueFactory(cdf -> Bindings
				.when(cdf.getValue().isFemaleProperty())
				.then("Nu")
				.otherwise("Nam"));
		results.getColumns().add(phaiCol);

		TableColumn<SinhVienModel, String> diaChiCol = new TableColumn<>("DIACHI");
		diaChiCol.setCellValueFactory(cdf -> cdf.getValue().diaChiProperty());
		results.getColumns().add(diaChiCol);

		DateColumn<SinhVienModel> ngaySinhCol = new DateColumn<>("NGAYSINH",
				cdf -> Bindings.createObjectBinding(() -> cdf.getValue().getNgaySinh().toLocalDate(),
						cdf.getValue().ngaySinhProperty()));
		results.getColumns().add(ngaySinhCol);

		TableColumn<SinhVienModel, String> daNghiHocCol = new TableColumn<>("DANGHIHOC");
		daNghiHocCol.setCellValueFactory(cdf -> Bindings
				.when(cdf.getValue().daNghiHocProperty())
				.then("Da nghi hoc")
				.otherwise("Con hoc"));
		results.getColumns().add(daNghiHocCol);

		return results;
	}

}
