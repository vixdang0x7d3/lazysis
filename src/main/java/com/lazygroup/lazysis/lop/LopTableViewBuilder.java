package com.lazygroup.lazysis.lop;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class LopTableViewBuilder implements Builder<Region> {

	LopModel model;

	LopTableViewBuilder(LopModel model) {
		this.model = model;
	}

	@Override
	public Region build() {
		TableView<LopModelItem> results = new TableView<>();

		TableColumn<LopModelItem, String> maLopCol = new TableColumn<>("MALOP");
		maLopCol.setCellValueFactory(cdf -> cdf.getValue().maLopProperty());
		results.getColumns().add(maLopCol);

		TableColumn<LopModelItem, String> tenLopCol = new TableColumn<>("TENLOP");
		tenLopCol.setCellValueFactory(cdf -> cdf.getValue().tenLopProperty());
		results.getColumns().add(tenLopCol);

		TableColumn<LopModelItem, String> khoaHocCol = new TableColumn<>("KHOAHOC");
		khoaHocCol.setCellValueFactory(cdf -> cdf.getValue().khoaHocProperty());
		results.getColumns().add(khoaHocCol);

		TableColumn<LopModelItem, String> maKhoaCol = new TableColumn<>("MAKHOA");
		maKhoaCol.setCellValueFactory(cdf -> cdf.getValue().maKhoaProperty());
		results.getColumns().add(maKhoaCol);

		results.setItems(model.getAllItems());
		model.selectedItemProperty().bind(results.getSelectionModel().selectedItemProperty());

		results.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
		VBox.setVgrow(results, Priority.ALWAYS);

		return results;
	}

}
