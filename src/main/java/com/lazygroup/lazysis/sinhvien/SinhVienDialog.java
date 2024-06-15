package com.lazygroup.lazysis.sinhvien;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.lazygroup.lazysis.util.Labels;
import com.lazygroup.lazysis.util.TwoColumnGridPane;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class SinhVienDialog extends Dialog<SinhVienModelItem> {

	private final SinhVienModelItem sinhvien;

	SinhVienDialog(SinhVienModelItem inModel) {
		this.sinhvien = (inModel == null) ? new SinhVienModelItem() : inModel;

		setDialogPane(createDialogPane());
		setResultConverter(buttonType -> {
			if (buttonType == ButtonType.OK) {
				return sinhvien;
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
		results.addTextFieldRow(results, 0, "Ma SV: ", sinhvien.maSvProperty());
		results.addTextFieldRow(results, 1, "Ho: ", sinhvien.maSvProperty());
		results.addTextFieldRow(results, 2, "Ten: ", sinhvien.maSvProperty());

		results.add(Labels.prompt("Phai: "), 0, 3);
		results.add(createPhaiComboBox(), 1, 3);
		results.addTextFieldRow(results, 4, "Dia chi: ", sinhvien.diaChiProperty());
		results.add(Labels.prompt("Ngay sinh: "), 0, 5);
		results.add(createNgaySinhDatePicker(), 1, 5);
		results.add(Labels.prompt("Ma lop: "), 0, 6);
		results.add(createMaLopComboBox(), 1, 6);
		results.add(Labels.prompt("Da nghi hoc: "), 0, 7);
		results.add(createCheckBoxDaNghiHoc(), 1, 7);
		results.add(Labels.prompt("Password: "), 0, 8);
		results.add(createBoundPasswordField(sinhvien.passwordProperty()), 1, 8);

		return results;
	}

	private PasswordField createBoundPasswordField(StringProperty boundProperty) {
		PasswordField passwordField = new PasswordField();
		passwordField.textProperty().bindBidirectional(sinhvien.passwordProperty());
		return passwordField;
	}

	private CheckBox createCheckBoxDaNghiHoc() {
		CheckBox checkBox = new CheckBox();
		checkBox.selectedProperty().bindBidirectional(sinhvien.daNghiHocProperty());
		return checkBox;
	}

	private ComboBox<String> createMaLopComboBox() {
		ComboBox<String> comboBox = new ComboBox<>();

		// TODO: fill items
		comboBox.getItems().addAll();
		comboBox.valueProperty().bindBidirectional(sinhvien.maLopProperty());

		return comboBox;
	}

	private DatePicker createNgaySinhDatePicker() {
		DatePicker datePicker = new DatePicker();

		datePicker.valueProperty().bind(new ObjectBinding<LocalDate>() {

			{
				super.bind(sinhvien.ngaySinhProperty());
			}

			@Override
			protected LocalDate computeValue() {
				return sinhvien.ngaySinhProperty().get().toLocalDate();
			}

		});

		sinhvien.ngaySinhProperty().bind(new ObjectBinding<LocalDateTime>() {

			{
				super.bind(datePicker.valueProperty());
			}

			@Override
			protected LocalDateTime computeValue() {
				return datePicker.valueProperty().get().atStartOfDay();
			}

		});
		return datePicker;
	}

	private ComboBox<String> createPhaiComboBox() {
		ComboBox<String> comboBox = new ComboBox<>();

		comboBox.getItems().addAll("Nam", "Nu");
		comboBox.getSelectionModel().select(0);

		comboBox.valueProperty().bind(new ObjectBinding<String>() {

			{
				super.bind(sinhvien.isFemaleProperty());
			}

			@Override
			protected String computeValue() {
				return sinhvien.isFemaleProperty().get() ? "Yes" : "No";
			}

		});
		sinhvien.isFemaleProperty().bind(
				Bindings.when(comboBox.valueProperty().isEqualTo("Nam"))
						.then(false)
						.otherwise(true));

		return comboBox;
	}
}
