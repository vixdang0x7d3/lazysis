package com.lazygroup.lazysis.sinhvien;

import org.springframework.util.Assert;

import javafx.scene.layout.Region;
import javafx.util.Builder;
import net.rgielen.fxweaver.core.FxWeaver;

public class SinhVienViewBuilder implements Builder<Region> {

	private FxWeaver fxWeaver;
	private Runnable loadModelHandler;

	SinhVienViewBuilder(FxWeaver fxWeaver, Runnable loadModelHandler) {
		this.fxWeaver = fxWeaver;
		this.loadModelHandler = loadModelHandler;
	}

	@Override
	public Region build() {

		fxWeaver.loadController(SinhVienViewFxmlController.class)
				.setLoadModelHandler(loadModelHandler);
		return fxWeaver.loadView(SinhVienViewFxmlController.class);
	}

}
