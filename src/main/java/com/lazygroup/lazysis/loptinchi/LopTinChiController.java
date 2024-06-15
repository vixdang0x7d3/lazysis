package com.lazygroup.lazysis.loptinchi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.rgielen.fxweaver.core.FxWeaver;

@Component
public class LopTinChiController {

	private final LopTinChiModel model;
	private final LopTinChiInteractor interactor;

	@Autowired
	LopTinChiController(LopTinChiModel model, LopTinChiInteractor interactor, FxWeaver fxWeaver) {
		this.model = model;
		this.interactor = interactor;
	}
}
