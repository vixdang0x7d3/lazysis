package com.lazygroup.lazysis.root;

import javafx.util.Builder;
import net.rgielen.fxweaver.core.FxWeaver;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class RootViewBuilder implements Builder<Region> {

	private final RootModel model;
	private final FxWeaver fxWeaver;
	private final Region loginView;
	private final Scene primaryScene;

	RootViewBuilder(RootModel model, Scene primaryScene, FxWeaver fxWeaver, Region loginView) {
		this.model = model;
		this.fxWeaver = fxWeaver;
		this.loginView = loginView;
		this.primaryScene = primaryScene;
	}

	public Region beforeLoginView() {
		return loginView;
	}

	public Region afterLoginView() {
		return fxWeaver.loadView(RootViewFxmlController.class);
	}

	@Override
	public Region build() {

		VBox results = new VBox();
		results.setMinWidth(1200.0);
		results.setMinHeight(800.0);

		results.getChildren().add(beforeLoginView());
		results.setAlignment(Pos.CENTER);

		model.loggedInProperty().addListener((ob, oldVal, newVal) -> {
			if (newVal == false) {
				results.getChildren().clear();
				results.getChildren().add(beforeLoginView());
			} else {
				results.getChildren().clear();

				Region view = afterLoginView();
				VBox.setVgrow(view, Priority.ALWAYS);

				results.getChildren().add(view);
			}

			primaryScene.getWindow().sizeToScene();
			primaryScene.getWindow().centerOnScreen();
		});

		return results;
	}
}
