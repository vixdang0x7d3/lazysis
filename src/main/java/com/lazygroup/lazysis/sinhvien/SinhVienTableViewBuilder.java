package com.lazygroup.lazysis.sinhvien;

import com.lazygroup.lazysis.util.DateColumn;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class SinhVienTableViewBuilder implements Builder<Region> {

	private final SinhVienModel model;

	public SinhVienTableViewBuilder(SinhVienModel model) {
		this.model = model;
	}

	@Override
	public Region build() {
		TableView<SinhVienModelItem> results = new TableView<>();

		TableColumn<SinhVienModelItem, String> maSvCol = new TableColumn<>("MASV");
		maSvCol.setCellValueFactory(cdf -> cdf.getValue().maSvProperty());
		results.getColumns().add(maSvCol);

		TableColumn<SinhVienModelItem, String> hoCol = new TableColumn<>("HO");
		hoCol.setCellValueFactory(cdf -> cdf.getValue().hoProperty());
		results.getColumns().add(hoCol);

		TableColumn<SinhVienModelItem, String> tenCol = new TableColumn<>("TEN");
		tenCol.setCellValueFactory(cdf -> cdf.getValue().tenProperty());
		results.getColumns().add(tenCol);

		TableColumn<SinhVienModelItem, String> phaiCol = new TableColumn<>("PHAI");
		phaiCol.setCellValueFactory(cdf -> Bindings
				.when(cdf.getValue().isFemaleProperty())
				.then("Nu")
				.otherwise("Nam"));
		results.getColumns().add(phaiCol);

		TableColumn<SinhVienModelItem, String> diaChiCol = new TableColumn<>("DIACHI");
		diaChiCol.setCellValueFactory(cdf -> cdf.getValue().diaChiProperty());
		results.getColumns().add(diaChiCol);

		DateColumn<SinhVienModelItem> ngaySinhCol = new DateColumn<>("NGAYSINH",
				cdf -> Bindings.createObjectBinding(() -> {
					return (cdf.getValue().getNgaySinh() != null)
							? cdf.getValue().getNgaySinh().toLocalDate()
							: null;
				}, cdf.getValue().ngaySinhProperty()));

		results.getColumns().add(ngaySinhCol);

		TableColumn<SinhVienModelItem, String> daNghiHocCol = new TableColumn<>("DANGHIHOC");
		daNghiHocCol.setCellValueFactory(cdf -> Bindings
				.when(cdf.getValue().daNghiHocProperty())
				.then("Da nghi hoc")
				.otherwise("Con hoc"));
		results.getColumns().add(daNghiHocCol);

		results.setItems(model.getAllItems());
		model.selectedItemProperty().bind(results.getSelectionModel().selectedItemProperty());

		results.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);

		VBox.setVgrow(results, Priority.ALWAYS);

		return results;
	}

}
