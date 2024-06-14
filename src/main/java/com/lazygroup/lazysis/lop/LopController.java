package com.lazygroup.lazysis.lop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LopController {

	private final LopModel model;

	@Autowired
	LopController(LopModel model) {
		this.model = model;
	}

}
