package com.lazygroup.lazysis;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.lazygroup.lazysis.event.StageReadyEvent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class JavaFxApp extends Application {

	private ConfigurableApplicationContext context;

	@Override
	public void init() {
		this.context = new SpringApplicationBuilder()
				.sources(LazysisApplication.class)
				.run(getParameters().getRaw().toArray(new String[0]));
	}

	@Override
	public void stop() {
		this.context.close();
		Platform.exit();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.context.publishEvent(new StageReadyEvent(primaryStage));
	}
}
