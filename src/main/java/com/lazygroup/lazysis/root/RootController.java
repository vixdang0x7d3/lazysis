package com.lazygroup.lazysis.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.home.HomeController;
import com.lazygroup.lazysis.login.LoginController;
import com.lazygroup.lazysis.sinhvien.SinhVienController;

import jakarta.annotation.PostConstruct;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxWeaver;

@Slf4j
@Component
public class RootController {

	private final RootModel model;
	private final RootInteractor interactor;
	private final RootViewBuilder rootViewBuilder;

	private final LoginController loginController;
	private final HomeController homeController;
	private final SinhVienController sinhvienController;

	@Autowired
	RootController(RootModel model, RootInteractor interactor, Scene primaryScene, FxWeaver fxWeaver,
			LoginController loginController,
			HomeController homeController,
			SinhVienController sinhvienController) {
		this.model = model;
		this.interactor = interactor;
		this.rootViewBuilder = new RootViewBuilder(model, primaryScene, fxWeaver,
				loginController.getView());

		// encapsulated controllers
		this.loginController = loginController;
		this.homeController = homeController;
		this.sinhvienController = sinhvienController;

		model.siteProperty().addListener((ob) -> System.out.println("site invalidated"));
	}

	@PostConstruct
	public void initEncapsulatedControllers() {
		loginController.setOuterPostLoginHandler(interactor::updateModelPostLogin);
		loginController.bindToParentModel(model.usernameProperty(), model.passwordProperty(),
				model.siteProperty());

		homeController.bindToParentModel(model.inAppNameProperty(), model.usernameProperty(),
				model.groupProperty(), model.lopProperty());

		sinhvienController.bindToParentModel(model.usernameProperty(), model.passwordProperty(),
				model.siteProperty(), model.loggedInProperty());
	}

	public Region getView() {
		return rootViewBuilder.build();
	}
}
