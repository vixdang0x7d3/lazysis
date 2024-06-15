package com.lazygroup.lazysis.login;

import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.lazygroup.lazysis.exception.InvalidCredentialException;

import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

@Component
public class LoginController {

	private final LoginModel model;
	private final LoginInteractor interactor;
	private final LoginViewBuilder viewBuilder;
	private Consumer<LoginDto> outerPostLoginHandler;

	public LoginController(LoginModel model, LoginInteractor interactor,
			@Qualifier("siteIdLookUpMap") Map<String, String> siteIdLookUpMap) {

		this.model = model;
		this.interactor = interactor;
		this.viewBuilder = new LoginViewBuilder(model, siteIdLookUpMap, this::login);

	}

	// init method
	public void setOuterPostLoginHandler(Consumer<LoginDto> handler) {
		this.outerPostLoginHandler = handler;
	}

	// init method
	public void bindToParentModel(StringProperty username, StringProperty password, StringProperty site) {
		model.usernameProperty().bind(username);
		model.passwordProperty().bind(password);
		model.siteProperty().bind(site);
	}

	public void login(Runnable postLoginGuiAction) {

		Assert.notNull(outerPostLoginHandler, "outer post-login handler is not initialized");

		Task<LoginDto> loginTask = new Task<LoginDto>() {

			@Override
			protected LoginDto call() throws InvalidCredentialException {
				return interactor.login();
			}

		};

		loginTask.setOnSucceeded(evt -> {
			LoginDto loginDto = loginTask.getValue();
			outerPostLoginHandler.accept(loginDto);

			postLoginGuiAction.run();
		});

		loginTask.setOnFailed(evt -> {
			String errorMsg = loginTask.getException().getMessage();
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Login failed");
			alert.setContentText(errorMsg);
			alert.show();

			postLoginGuiAction.run();
		});

		Thread loginThread = new Thread(loginTask);
		loginThread.start();
	}

	public Region getView() {
		return viewBuilder.build();
	}
}
