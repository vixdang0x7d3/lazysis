package com.lazygroup.lazysis.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.home.HomeController;
import com.lazygroup.lazysis.login.LoginController;

import jakarta.annotation.PostConstruct;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import net.rgielen.fxweaver.core.FxWeaver;

@Component
public class RootController {

	private final RootModel model;
	private final RootInteractor interactor;
	private final RootViewBuilder rootViewBuilder;

	private final LoginController loginController;
	private final HomeController homeController;

	@Autowired
	RootController(RootModel model, RootInteractor interactor, Scene primaryScene, FxWeaver fxWeaver,
			LoginController loginController,
			HomeController homeController) {
		this.model = model;
		this.interactor = interactor;
		this.rootViewBuilder = new RootViewBuilder(model, primaryScene, fxWeaver,
				loginController.getView());

		// encapsulated controllers
		this.loginController = loginController;
		this.homeController = homeController;
	}

	@PostConstruct
	public void initEncapsulatedControllers() {
		loginController.setOuterPostLoginHandler(interactor::updateModelPostLogin);
		loginController.bindToParentModel(model.usernameProperty(), model.passwordProperty(),
				model.siteProperty());

		homeController.bindToParentModel(model.inAppNameProperty(), model.usernameProperty(),
				model.groupProperty(), model.lopProperty());
	}

	public Region getView() {
		return rootViewBuilder.build();
	}
}
