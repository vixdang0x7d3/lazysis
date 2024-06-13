package com.lazygroup.lazysis.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import javafx.stage.Stage;
import lombok.Getter;

public class StageReadyEvent extends ApplicationEvent {

	@Getter
	private final Stage stage;

	public StageReadyEvent(Stage stage) {
		super(stage);
		this.stage = stage;
	}
}
