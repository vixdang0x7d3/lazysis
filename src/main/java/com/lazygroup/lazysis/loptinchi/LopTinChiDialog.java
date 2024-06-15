package com.lazygroup.lazysis.loptinchi;

import com.lazygroup.lazysis.util.Labels;
import com.lazygroup.lazysis.util.Texts;
import com.lazygroup.lazysis.util.TwoColumnGridPane;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class LopTinChiDialog extends Dialog<LopTinChiModelItem> {

	private final LopTinChiModelItem loptinchi;
	private final StringProperty huyLopDisplayString = new SimpleStringProperty("");

	LopTinChiDialog(LopTinChiModelItem inModel) {
		this.loptinchi = (inModel == null) ? new LopTinChiModelItem() : inModel;

		huyLopDisplayString.bind(Bindings.when(loptinchi.huyLopProperty())
				.then("Da huy")
				.otherwise("Khong"));

		setDialogPane(createDialogPane());
		setResultConverter(buttonType -> {
			if (buttonType == ButtonType.OK) {
				return loptinchi;
			}
			return null;
		});
	}

	private DialogPane createDialogPane() {

		DialogPane results = new DialogPane();

		results.setHeader(Labels.h3("Sinhvien Dialog"));
		results.setContent(createContent());
		results.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		return results;
	}

	private Region createContent() {
		HBox results = new HBox(createDataEntry());
		return results;
	}

	private Region createDataEntry() {
		TwoColumnGridPane results = new TwoColumnGridPane();

		results.add(Labels.prompt("MaLTC: "), 0, 0);
		results.add(Texts.integerField(loptinchi.maLtcProperty().asObject(), 100.0), 0, 1);
		results.add(Labels.prompt("NienKhoa: "), 0, 1);
		results.add(createNienKhoaComboBox(), 1, 1);
		results.add(Labels.prompt("HocKy: "), 0, 2);
		results.add(createHocKyComboBox(), 1, 2);
		results.add(Labels.prompt("MaMH: "), 0, 3);
		results.add(createMaMhComboBox(), 1, 3);
		results.add(Labels.prompt("MaGV: "), 0, 4);
		results.add(createMaGvComboBox(), 1, 4);
		results.addDisplayRow(results, 5, "HuyLop: ", huyLopDisplayString);
		results.add(Labels.prompt("SoSVToiThieu: "), 0, 6);
		results.add(Texts.integerField(loptinchi.soSvToiThieuProperty().asObject(), 100.0), 1, 6);
		results.addDisplayRow(results, 7, "HuyLop: ", huyLopDisplayString);

		return results;
	}

	private ComboBox<String> createNienKhoaComboBox() {
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.getItems().addAll(); // TODO: add items

		comboBox.valueProperty().bindBidirectional(loptinchi.nienKhoaProperty());

		return comboBox;
	}

	private ComboBox<Integer> createHocKyComboBox() {
		ComboBox<Integer> comboBox = new ComboBox<>();
		comboBox.getItems().addAll(1, 2);
		comboBox.valueProperty().bindBidirectional(loptinchi.hocKyProperty().asObject());

		return comboBox;
	}

	private ComboBox<String> createMaMhComboBox() {

		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.getItems().addAll(); // TODO: add items
		comboBox.valueProperty().bindBidirectional(loptinchi.maMhProperty());

		return comboBox;
	}

	private ComboBox<Integer> createNhomComboBox() {
		ComboBox<Integer> comboBox = new ComboBox<>();
		comboBox.getItems().addAll(1, 2);
		comboBox.valueProperty().bindBidirectional(loptinchi.nhomProperty().asObject());

		return comboBox;
	}

	private ComboBox<String> createMaGvComboBox() {
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.getItems().addAll(); // TODO: add items
		comboBox.valueProperty().bindBidirectional(loptinchi.maGvProperty());
		return comboBox;
	}
}
