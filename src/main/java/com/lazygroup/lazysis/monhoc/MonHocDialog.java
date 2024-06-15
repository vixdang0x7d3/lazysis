package com.lazygroup.lazysis.monhoc;

import com.lazygroup.lazysis.util.Labels;
import com.lazygroup.lazysis.util.Texts;
import com.lazygroup.lazysis.util.TwoColumnGridPane;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class MonHocDialog extends Dialog<MonHocModelItem> {

	private final MonHocModelItem monhoc;

	MonHocDialog(MonHocModelItem inModel) {
		this.monhoc = inModel == null ? new MonHocModelItem() : inModel;

		setDialogPane(createDialogPane());
		setResultConverter(buttonType -> {
			if (buttonType == ButtonType.OK) {
				return monhoc;
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
		results.addTextFieldRow(results, 0, "Ma MH: ", monhoc.maMhProperty());
		results.addTextFieldRow(results, 1, "Ten MH: ", monhoc.tenMhProperty());
		results.add(Labels.prompt("So Tiet LH: "), 0, 2);
		results.add(Texts.integerField(monhoc.soTietThProperty().asObject(), 100.0), 1, 2);
		results.add(Labels.prompt("So Tiet TH: "), 0, 3);
		results.add(Texts.integerField(monhoc.soTietLtProperty().asObject(), 100.0), 1, 3);

		return results;
	}
}
