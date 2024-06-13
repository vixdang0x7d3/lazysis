package com.lazygroup.lazysis.sinhvien;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;

@FxmlView("sinhvienView.fxml")
@Component
public class SinhVienViewFxmlController implements Initializable {

	@FXML
	VBox results;

	@FXML
	Button undoButton, redoButton, themButton, xoaButton, suaButton;

	private final ObservableList<SinhVienModel> model;

	@Autowired
	SinhVienViewFxmlController(ObservableList<SinhVienModel> dSinhVienModel) {
		this.model = dSinhVienModel;
	}

	private Region createSinhVienTableView() {
		return new SinhVienTableViewBuilder(model).build();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		results.getChildren().add(createSinhVienTableView());
	}

}
