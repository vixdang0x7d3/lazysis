package com.lazygroup.lazysis.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.root.RootController;

import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
public class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {

	private final String appTitle;
	private final RootController rootController;
	private final Scene primaryScene;

	@Autowired
	public PrimaryStageInitializer(Scene primaryScene, RootController rootController,
			@Value("${app.title}") String appTitle) {
		this.primaryScene = primaryScene;
		this.rootController = rootController;
		this.appTitle = appTitle;
	}

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		Stage stage = event.getStage();
		primaryScene.setRoot(rootController.getView());

		stage.setResizable(false);
		stage.setScene(primaryScene);
		stage.setTitle(this.appTitle);
		stage.show();
	}
}
