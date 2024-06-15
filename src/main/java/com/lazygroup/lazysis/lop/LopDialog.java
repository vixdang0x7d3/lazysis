package com.lazygroup.lazysis.lop;

import com.lazygroup.lazysis.util.Labels;
import com.lazygroup.lazysis.util.TwoColumnGridPane;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class LopDialog extends Dialog<LopModelItem> {

	private final LopModelItem lop;

	LopDialog(LopModelItem inModel) {
		this.lop = (inModel == null) ? new LopModelItem() : inModel;

		setDialogPane(createDialogPane());
		setResultConverter(buttonType -> {
			if (buttonType == ButtonType.OK) {
				return lop;
			}
			return null;
		});
	}

	private DialogPane createDialogPane() {

		DialogPane results = new DialogPane();

		results.setHeader(Labels.h3("Lop Dialog"));
		results.setContent(createContent());
		results.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		return results;
	}

	private Region createContent() {
		HBox results = new HBox(createDataEntry());
		return results;
	}

	private GridPane createDataEntry() {

		TwoColumnGridPane results = new TwoColumnGridPane();
		results.addTextFieldRow(results, 0, "Ma lop: ", lop.maLopProperty());
		results.addTextFieldRow(results, 1, "Ten lop: ", lop.tenLopProperty());
		results.addTextFieldRow(results, 2, "Khoa hoc: ", lop.khoaHocProperty());
		results.add(Labels.prompt("Ma khoa: "), 0, 3);
		results.add(createMaKhoaComboBox(), 1, 3);

		return results;
	}

	private ComboBox<String> createMaKhoaComboBox() {

		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.getItems().addAll( /* TODO: set comboBox items */ );

		lop.maKhoaProperty().bindBidirectional(comboBox.valueProperty());

		return comboBox;
	}
}
