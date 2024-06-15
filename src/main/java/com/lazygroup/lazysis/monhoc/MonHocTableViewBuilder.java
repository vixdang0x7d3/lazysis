package com.lazygroup.lazysis.monhoc;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class MonHocTableViewBuilder implements Builder<Region> {

	private final MonHocModel model;

	MonHocTableViewBuilder(MonHocModel model) {
		this.model = model;
	}

	@Override
	public Region build() {

		TableView<MonHocModelItem> results = new TableView<>();

		TableColumn<MonHocModelItem, String> maMhCol = new TableColumn<>("MAMH");
		maMhCol.setCellValueFactory(cdf -> cdf.getValue().maMhProperty());
		results.getColumns().add(maMhCol);

		TableColumn<MonHocModelItem, String> tenMhCol = new TableColumn<>("TENMH");
		tenMhCol.setCellValueFactory(cdf -> cdf.getValue().tenMhProperty());
		results.getColumns().add(tenMhCol);

		TableColumn<MonHocModelItem, Integer> soTietLtCol = new TableColumn<>("SOTIET_LT");
		soTietLtCol.setCellValueFactory(cdf -> cdf.getValue().soTietLtProperty().asObject());
		results.getColumns().add(soTietLtCol);

		TableColumn<MonHocModelItem, Integer> soTietThCol = new TableColumn<>("SOTIET_TH");
		soTietThCol.setCellValueFactory(cdf -> cdf.getValue().soTietThProperty().asObject());
		results.getColumns().add(soTietThCol);

		results.setItems(model.getAllItems());
		model.selectedItemProperty().bind(results.getSelectionModel().selectedItemProperty());

		results.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
		VBox.setVgrow(results, Priority.ALWAYS);

		return results;
	}

}
