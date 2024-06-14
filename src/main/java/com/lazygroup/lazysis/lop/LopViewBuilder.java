package com.lazygroup.lazysis.lop;

import javafx.scene.layout.Region;
import javafx.util.Builder;
import net.rgielen.fxweaver.core.FxWeaver;

public class LopViewBuilder implements Builder<Region> {

	private final LopModel model;

	private final FxWeaver fxWeaver;

	LopViewBuilder(LopModel model, FxWeaver fxWeaver) {
		this.model = model;
		this.fxWeaver = fxWeaver;
	}

	@Override
	public Region build() {
		return fxWeaver.loadView(LopViewFxmlController.class);
	}

}
