package com.lazygroup.lazysis.login;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import com.lazygroup.lazysis.util.Labels;
import com.lazygroup.lazysis.util.Texts;

//  TODO: refactor commonly used ui components to a util class

public class LoginViewBuilder implements Builder<Region> {

	private final String headingText = "LAZYSIS LOGIN";

	private final String usernamePrompt = "USERNAME: ";

	private String passwordPrompt = "PASSWORD: ";

	private LoginModel model;
	private Map<String, String> idLookUp;
	private Consumer<Runnable> loginHandler;

	LoginViewBuilder(LoginModel loginModel, Map<String, String> idLookUp, Consumer<Runnable> loginHandler) {
		this.model = loginModel;
		this.loginHandler = loginHandler;
		this.idLookUp = idLookUp;
	}

	@Override
	public Region build() {
		BorderPane results = new BorderPane();
		results.getStylesheets()
				.add(Objects.requireNonNull(this.getClass().getResource("/css/login.css"))
						.toExternalForm());

		results.setTop(Labels.h3(headingText));
		results.setCenter(createDataEntry());
		results.setBottom(createButtons());

		BorderPane.setAlignment(results.getTop(), Pos.CENTER);
		results.setPadding(new Insets(10));

		return results;
	}

	private Node createDataEntry() {
		VBox results = new VBox(6, createComboBox(), createRadioButtons(), usernameBox(), passwordBox());
		results.setPadding(new Insets(20, 0, 0, 0));
		results.setAlignment(Pos.CENTER);
		return results;
	}

	private Node createComboBox() {
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.getItems().addAll(idLookUp.keySet());

		model.siteProperty().bind(Bindings.createStringBinding(() -> {

			if (comboBox.valueProperty().get() == null)
				return "";

			return idLookUp.get(comboBox.valueProperty().get());

		}, comboBox.valueProperty()));

		var results = new HBox(6, comboBox);
		results.setAlignment(Pos.CENTER);

		return results;
	}

	private Node createRadioButtons() {

		RadioButton gvRadioButton = new RadioButton("Giao Vien");
		RadioButton svRadioButton = new RadioButton("Sinh Vien");

		ToggleGroup toggleGroup = new ToggleGroup();

		svRadioButton.setToggleGroup(toggleGroup);
		gvRadioButton.setToggleGroup(toggleGroup);

		gvRadioButton.setSelected(true);

		model.isSvLoginProperty().bindBidirectional(svRadioButton.selectedProperty());

		HBox results = new HBox(10, svRadioButton, gvRadioButton);
		results.setAlignment(Pos.CENTER);
		results.setPadding(new Insets(20));

		return results;
	}

	private Node createButtons() {

		Button loginButton = new Button("Login");
		loginButton.disableProperty().bind(model.okToLoginProperty().not());

		loginButton.setOnAction(evt -> {
			loginButton.disableProperty().unbind();
			loginButton.setDisable(true);
			loginHandler.accept(() -> {
				loginButton.disableProperty().bind(model.okToLoginProperty().not());
			});
		});

		HBox results = new HBox(10, loginButton);
		results.setAlignment(Pos.CENTER);
		results.setPadding(new Insets(20));
		return results;
	}

	private Node usernameBox() {
		Label boundPromptLabel = Labels.prompt(usernamePrompt);
		boundPromptLabel.textProperty().bind(Bindings
				.when(model.isSvLoginProperty())
				.then("MSSV:     ")
				.otherwise("USERNAME: "));

		HBox results = new HBox(6, boundPromptLabel, Texts.textField(model.usernameProperty()));
		results.setAlignment(Pos.CENTER);

		return results;
	}

	private Node passwordBox() {
		HBox results = new HBox(6, Labels.prompt(passwordPrompt), boundPasswordField(model.passwordProperty()));
		results.setAlignment(Pos.CENTER);
		return results;
	}

	private Node boundPasswordField(StringProperty boundProperty) {
		PasswordField passwordField = new PasswordField();
		passwordField.textProperty().bindBidirectional(boundProperty);
		return passwordField;
	}
}
