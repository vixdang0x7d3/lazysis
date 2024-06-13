package com.lazygroup.lazysis;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;

@SpringBootApplication
public class LazysisApplication {
	public static void main(String[] args) {
		Application.launch(JavaFxApp.class, args);
	}

	@Bean
	public Scene primaryScene() {
		return new Scene(new VBox());
	}

	@Bean
	public FxWeaver fxWeaver(ConfigurableApplicationContext applicationContext) {
		// Would also work with javafx-weaver-core only:
		// return new FxWeaver(applicationContext::getBean, applicationContext::close);
		return new SpringFxWeaver(applicationContext); // (2)
	}
}
