package com.lazygroup.lazysis.loptinchi;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;

@FxmlView("loptinchiView.fxml")
@Component
public class LopTinChiViewFxmlController implements Initializable {

	@FXML
	VBox results;
	@FXML
	Button themButton, xoaButton, suaButton, undoButton, redoButton;

	private final LopTinChiModel model;

	@Autowired
	LopTinChiViewFxmlController(LopTinChiModel model) {
		this.model = model;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'initialize'");
	}

}
