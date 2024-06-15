package com.lazygroup.lazysis.dangky;

import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.dao.Dao;
import com.lazygroup.lazysis.util.DatabaseUtils;

@Component
public class DangKyInteractor {

	private final DangKyModel model;
	private final Dao<DangKy, Object[]> dao;
	private final DatabaseUtils dbUtils;

	DangKyInteractor(DangKyModel model, DangKyDaoImpl dao, DatabaseUtils dbUtils) {
		this.model = model;
		this.dao = dao;
		this.dbUtils = dbUtils;
	}

	public List


}
