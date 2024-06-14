package com.lazygroup.lazysis.lop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.util.DatabaseUtils;

@Component
public class LopInteractor {

	private final LopModel model;
	private final LopDao dao;
	private final DatabaseUtils dbUtils;

	@Autowired
	LopInteractor(LopModel model, LopDao dao, DatabaseUtils dbUtils) {
		this.model = model;
		this.dao = dao;
		this.dbUtils = dbUtils;
	}

	public List<LopModelItem> fetchData() {
		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

	}

	private LopModelItem createModelItemFromLop(Lop lop) {
		LopModelItem modelItem = new LopModelItem();

		modelItem.setMaLop(lop.getMaLop());
		modelItem.setTenLop(lop.getTenLop());
		modelItem.setKhoaHoc(lop.getKhoaHoc());
		modelItem.setMaKhoa(lop.getMaKhoa());

		return modelItem;
	}

	private Lop createLopFromModelItem(LopModelItem modelItem) {
		Lop lop = Lop.builder()
				.maLop(modelItem.getMaLop())
				.tenLop(modelItem.getTenLop())
				.khoaHoc(modelItem.getKhoaHoc())
				.maKhoa(modelItem.getMaKhoa())
				.build();

		return lop;
	}
}
