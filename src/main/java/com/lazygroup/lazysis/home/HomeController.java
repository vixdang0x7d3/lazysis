package com.lazygroup.lazysis.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.beans.property.StringProperty;
import javafx.scene.layout.Region;
import net.rgielen.fxweaver.core.FxWeaver;

@Component
public class HomeController {

	private HomeModel model;
	private HomeViewBuilder viewBuilder;

	@Autowired
	HomeController(HomeModel model, FxWeaver fxWeaver) {
		this.model = model;
		viewBuilder = new HomeViewBuilder(fxWeaver);
	}

	// init method
	public void bindToParentModel(StringProperty inAppNameProperty, StringProperty maGv_maSvProperty,
			StringProperty groupProperty, StringProperty maLopProperty) {

		model.inAppNameProperty().bind(inAppNameProperty);
		model.maGv_maSvProperty().bind(maGv_maSvProperty);
		model.groupProperty().bind(groupProperty);
		model.maLopProperty().bind(maLopProperty);

	}

	public Region getView() {
		return viewBuilder.build();
	}
}
