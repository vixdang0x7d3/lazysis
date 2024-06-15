package com.lazygroup.lazysis.monhoc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.dao.Dao;
import com.lazygroup.lazysis.util.DatabaseUtils;

@Component
public class MonHocInteractor {

	private final MonHocModel model;
	private final Dao<MonHoc, String> dao;
	private final DatabaseUtils dbUtils;

	@Autowired
	MonHocInteractor(MonHocModel model, DaoMonHocImpl dao, DatabaseUtils dbUtils) {
		this.model = model;
		this.dao = dao;
		this.dbUtils = dbUtils;
	}

	public List<MonHocModelItem> fetchData() {
		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		List<MonHocModelItem> data = dao.list().stream().map((mh) -> createModelItemFromMonHoc(mh))
				.collect(Collectors.toList());

		dbUtils.clearThreadBoundContext();

		return data;
	}

	public void themMonHoc(MonHocModelItem modelItem) {
		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		dao.create(createMonHocFromModelItem(modelItem));

		dbUtils.clearThreadBoundContext();
	}

	public void suaMonHoc(MonHocModelItem modelItem) {
		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		dao.update(createMonHocFromModelItem(modelItem), model.getSelectedItem().getMaMh());

		dbUtils.clearThreadBoundContext();
	}

	public void xoaMonHoc() {
		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		dao.delete(model.getSelectedItem().getMaMh());

		dbUtils.clearThreadBoundContext();

	}

	public void updateModel(List<MonHocModelItem> data) {
		model.getAllItems().setAll(data);
	}

	public void updateModel(MonHocModelItem data) {
		model.getAllItems().add(data);
	}

	public void updateModel(String maMh, MonHocModelItem data) {
		Optional<MonHocModelItem> oldItem = getMonHocModelItem(maMh);
		oldItem.ifPresent(model.getAllItems()::remove);

		updateModel(data);
	}

	public void updateModel(String maMh) {
		Optional<MonHocModelItem> oldItem = getMonHocModelItem(maMh);
		oldItem.ifPresent(model.getAllItems()::remove);
	}

	private Optional<MonHocModelItem> getMonHocModelItem(String maMh) {

		for (var mh : model.getAllItems()) {
			if (mh.getMaMh().equals(maMh)) {
				return Optional.of(mh);
			}
		}
		return Optional.empty();
	}

	private MonHoc createMonHocFromModelItem(MonHocModelItem modelItem) {
		MonHoc monhoc = MonHoc.builder()
				.maMh(modelItem.getMaMh())
				.tenMh(modelItem.getTenMh())
				.soTietLt(modelItem.getSoTietLt())
				.soTietTh(modelItem.getSoTietTh())
				.build();
		return monhoc;
	}

	private MonHocModelItem createModelItemFromMonHoc(MonHoc monhoc) {
		MonHocModelItem modelItem = new MonHocModelItem();
		modelItem.setMaMh(monhoc.getMaMh());
		modelItem.setTenMh(monhoc.getTenMh());
		modelItem.setSoTietLt(monhoc.getSoTietLt());
		modelItem.setSoTietTh(monhoc.getSoTietTh());

		return modelItem;
	}
}
