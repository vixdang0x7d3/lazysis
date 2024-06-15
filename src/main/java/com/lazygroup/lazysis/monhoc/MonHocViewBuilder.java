package com.lazygroup.lazysis.monhoc;

import javafx.scene.layout.Region;
import javafx.util.Builder;
import net.rgielen.fxweaver.core.FxWeaver;

public class MonHocViewBuilder implements Builder<Region> {

	private final MonHocModel model;
	private final FxWeaver fxWeaver;

	MonHocViewBuilder(MonHocModel model, FxWeaver fxWeaver) {
		this.model = model;
		this.fxWeaver = fxWeaver;
	}

	@Override
	public Region build() {
	}

}
