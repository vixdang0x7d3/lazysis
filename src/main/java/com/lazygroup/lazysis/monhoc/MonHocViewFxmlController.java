package com.lazygroup.lazysis.monhoc;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;

@FxmlView("monhocView.fxml")
@Component
public class MonHocViewFxmlController implements Initializable {

	@FXML
	VBox results;

	@FXML
	Button undoButton, redoButton, themButton, xoaButton, suaButton;

	private final MonHocModel model;

	@Autowired
	MonHocViewFxmlController(MonHocModel model) {
		this.model = model;
	}

	private Region createTableView() {
		return new MonHocTableViewBuilder(model).build();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		results.getChildren().add(createTableView());
	}

}
