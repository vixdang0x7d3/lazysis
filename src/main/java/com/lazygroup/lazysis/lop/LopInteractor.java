package com.lazygroup.lazysis.lop;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.dao.Dao;
import com.lazygroup.lazysis.util.DatabaseUtils;

@Component
public class LopInteractor {

	private final LopModel model;
	private final Dao<Lop, String> dao;
	private final DatabaseUtils dbUtils;

	@Autowired
	LopInteractor(LopModel model, LopDaoImpl dao, DatabaseUtils dbUtils) {
		this.model = model;
		this.dao = dao;
		this.dbUtils = dbUtils;
	}

	public List<LopModelItem> fetchData() {
		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		List<LopModelItem> data = dao.list().stream().map((lop) -> createModelItemFromLop(lop))
				.collect(Collectors.toList());

		dbUtils.clearThreadBoundContext();

		return data;
	}

	public void themLop(LopModelItem modelItem) {

		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		dao.create(createLopFromModelItem(modelItem));

		dbUtils.clearThreadBoundContext();
	}

	public void suaLop(LopModelItem modelItem) {
		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		dao.update(createLopFromModelItem(modelItem), model.getSelectedItem().getMaLop());

		dbUtils.clearThreadBoundContext();
	}

	public void xoaLop() {
		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		dao.delete(model.getSelectedItem().getMaLop());

		dbUtils.clearThreadBoundContext();
	}

	public void updateModel(List<LopModelItem> data) {
		model.getAllItems().setAll(data);
	}

	public void updateModel(LopModelItem data) {
		model.getAllItems().add(data);
	}

	public void updateModel(String maLop, LopModelItem data) {
		Optional<LopModelItem> oldItem = getLopModelItem(maLop);
		oldItem.ifPresent(model.getAllItems()::remove);

		updateModel(data);
	}

	public void updateModel(String maLop) {
		Optional<LopModelItem> item = getLopModelItem(maLop);
		item.ifPresent(model.getAllItems()::remove);
	}

	private Optional<LopModelItem> getLopModelItem(String maLop) {
		for (LopModelItem l : model.getAllItems()) {
			if (l.getMaLop().equals(maLop)) {
				return Optional.of(l);
			}
		}
		return Optional.empty();
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
