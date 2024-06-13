package com.lazygroup.lazysis.home;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("homeView.fxml")
public class HomeViewFxmlController implements Initializable {
	@FXML
	private VBox results;
	@FXML
	private Button changePasswordButton;
	@FXML
	private Button addLoginButton;
	@FXML
	private Label inAppUsernameLabel;
	@FXML
	private Label maGv_maSvPromptLabel;
	@FXML
	private Label maGv_maSvLabel;
	@FXML
	private Label groupLabel;

	@FXML
	private Label maLopPromptLabel;
	@FXML
	private Label maLopLabel;

	private final HomeModel model;

	@Autowired
	HomeViewFxmlController(HomeModel model) {
		this.model = model;
	}

	private void configureInAppNameLabel() {
		maGv_maSvPromptLabel.textProperty().bind(Bindings.when(model.groupProperty().isEqualTo("SV"))
				.then("MASV:")
				.otherwise("LOGIN:"));

		inAppUsernameLabel.textProperty().bind(model.inAppNameProperty());
	}

	private void configureMaGv_MaSvLabel() {
		maGv_maSvLabel.textProperty().bind(model.maGv_maSvProperty());
	}

	private void configureGroupLabel() {
		groupLabel.textProperty().bind(model.groupProperty());
	}

	private void configureMaLop() {
		maLopPromptLabel.visibleProperty().bind(model.maLopProperty().isEmpty().not());
		maLopLabel.visibleProperty().bind(model.maLopProperty().isEmpty().not());

		maLopLabel.textProperty().bind(model.maLopProperty());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		configureInAppNameLabel();
		configureMaGv_MaSvLabel();
		configureGroupLabel();
		configureMaLop();

		results.setSpacing(10);
		results.setPadding(new Insets(5));

	}

}
