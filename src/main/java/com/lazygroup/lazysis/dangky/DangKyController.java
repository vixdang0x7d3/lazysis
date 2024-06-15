package com.lazygroup.lazysis.dangky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DangKyController {

	private final DangKyModel model;
	private final DangKyInteractor interactor;

	@Autowired
	DangKyController(DangKyModel model) {
		this.model = model;
	}
}
