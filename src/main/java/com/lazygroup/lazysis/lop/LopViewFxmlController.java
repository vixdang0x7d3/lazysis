package com.lazygroup.lazysis.lop;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;

@FxmlView("lopView.fxml")
@Component
public class LopViewFxmlController implements Initializable {

	@FXML
	private VBox results;

	@FXML
	private Button themButton, xoaButton, suaButton, undoButton, redoButton;

	private final LopModel model;

	LopViewFxmlController(LopModel model) {
		this.model = model;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
