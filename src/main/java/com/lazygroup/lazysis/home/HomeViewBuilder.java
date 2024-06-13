package com.lazygroup.lazysis.home;

import javafx.scene.layout.Region;
import javafx.util.Builder;
import net.rgielen.fxweaver.core.FxWeaver;

public class HomeViewBuilder implements Builder<Region> {

	private FxWeaver fxWeaver;

	HomeViewBuilder(FxWeaver fxWeaver) {
		this.fxWeaver = fxWeaver;
	}

	@Override
	public Region build() {
		return fxWeaver.loadView(HomeViewFxmlController.class);
	}

}
