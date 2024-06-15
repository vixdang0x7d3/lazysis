package com.lazygroup.lazysis.monhoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonHocController {

	private final MonHocModel model;
	private final MonHocInteractor interactor;

	@Autowired
	MonHocController(MonHocModel model, MonHocInteractor interactor) {

	}
}
