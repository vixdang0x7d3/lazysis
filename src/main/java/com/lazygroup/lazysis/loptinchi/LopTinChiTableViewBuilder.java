package com.lazygroup.lazysis.loptinchi;

import javafx.beans.binding.Bindings;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

public class LopTinChiTableViewBuilder implements Builder<Region> {

	private final LopTinChiModel model;

	LopTinChiTableViewBuilder(LopTinChiModel model) {
		this.model = model;
	}

	@Override
	public Region build() {
		TableView<LopTinChiModelItem> results = new TableView<>();

		TableColumn<LopTinChiModelItem, Integer> maLtcCol = new TableColumn<>("MALTC");
		maLtcCol.setCellValueFactory(cdf -> cdf.getValue().maLtcProperty().asObject());
		results.getColumns().add(maLtcCol);

		TableColumn<LopTinChiModelItem, String> nienKhoaCol = new TableColumn<>("NIENKHOA");
		nienKhoaCol.setCellValueFactory(cdf -> cdf.getValue().nienKhoaProperty());
		results.getColumns().add(nienKhoaCol);

		TableColumn<LopTinChiModelItem, Integer> hocKyCol = new TableColumn<>("HOCKY");
		hocKyCol.setCellValueFactory(cdf -> cdf.getValue().hocKyProperty().asObject());
		results.getColumns().add(hocKyCol);

		TableColumn<LopTinChiModelItem, String> maMhCol = new TableColumn<>("MAMH");
		maMhCol.setCellValueFactory(cdf -> cdf.getValue().maMhProperty());
		results.getColumns().add(maMhCol);

		TableColumn<LopTinChiModelItem, Integer> nhomCol = new TableColumn<>("HOCKY");
		nhomCol.setCellValueFactory(cdf -> cdf.getValue().nhomProperty().asObject());
		results.getColumns().add(nhomCol);

		TableColumn<LopTinChiModelItem, String> maGvCol = new TableColumn<>("MAGV");
		maGvCol.setCellValueFactory(cdf -> cdf.getValue().maGvProperty());
		results.getColumns().add(maGvCol);

		TableColumn<LopTinChiModelItem, String> maKhoaCol = new TableColumn<>("MAKHOA");
		maKhoaCol.setCellValueFactory(cdf -> cdf.getValue().maKhoaProperty());
		results.getColumns().add(maKhoaCol);

		TableColumn<LopTinChiModelItem, Integer> soSvToiThieuCol = new TableColumn<>("SOSVTOITHIEU");
		soSvToiThieuCol.setCellValueFactory(cdf -> cdf.getValue().soSvToiThieuProperty().asObject());
		results.getColumns().add(soSvToiThieuCol);

		TableColumn<LopTinChiModelItem, String> huyLopCol = new TableColumn<>("HUYLOP");
		huyLopCol.setCellValueFactory(cdf -> Bindings.when(cdf.getValue().huyLopProperty())
				.then("Da Huy")
				.otherwise("Khong"));
		results.getColumns().add(soSvToiThieuCol);

		results.setItems(model.getAllItems());
		model.selectedItemProperty().bind(results.getSelectionModel().selectedItemProperty());

		results.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
		VBox.setVgrow(results, Priority.ALWAYS);

		return results;
	}

}
