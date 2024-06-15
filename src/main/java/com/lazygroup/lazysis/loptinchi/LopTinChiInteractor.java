package com.lazygroup.lazysis.loptinchi;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.dao.Dao;
import com.lazygroup.lazysis.util.DatabaseUtils;

@Component
public class LopTinChiInteractor {

	private final LopTinChiModel model;
	private final DatabaseUtils dbUtils;
	private final Dao<LopTinChi, Integer> dao;

	@Autowired
	LopTinChiInteractor(LopTinChiModel model, DatabaseUtils dbUtils, LopTinChiDaoImpl dao) {
		this.model = model;
		this.dao = dao;
		this.dbUtils = dbUtils;
	}

	public List<LopTinChiModelItem> fetchData() {

		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		var data = dao.list().stream().map((ltc) -> createModelItemFromLopTinChi(ltc))
				.collect(Collectors.toList());

		dbUtils.clearThreadBoundContext();

		return data;
	}

	private void updateModel(List<LopTinChiModelItem> data) {
		model.getAllItems().setAll(data);
	}

	private void updateModel(LopTinChiModelItem data) {
		model.getAllItems().add(data);
	}

	private void updateModel(Integer maLtc, LopTinChiModelItem data) {
		Optional<LopTinChiModelItem> oldItem = getLopTinChiModelItem(maLtc);
		oldItem.ifPresent(model.getAllItems()::remove);

		updateModel(data);
	}

	private void updateModel(Integer maLtc) {
		Optional<LopTinChiModelItem> oldItem = getLopTinChiModelItem(maLtc);
		oldItem.ifPresent(model.getAllItems()::remove);
	}

	private Optional<LopTinChiModelItem> getLopTinChiModelItem(Integer maLtc) {
		for (var ltc : model.getAllItems()) {
			if (ltc.getMaLtc() == (maLtc)) {
				return Optional.of(ltc);
			}
		}
		return Optional.empty();
	}

	private LopTinChiModelItem createModelItemFromLopTinChi(LopTinChi ltc) {
		LopTinChiModelItem modelItem = new LopTinChiModelItem();
		modelItem.setMaLtc(ltc.getMaLtc());
		modelItem.setNienKhoa(ltc.getNienKhoa());
		modelItem.setHocKy(ltc.getHocKy());
		modelItem.setMaMh(ltc.getMaMh());
		modelItem.setNhom(ltc.getNhom());
		modelItem.setMaGv(ltc.getMaGv());
		modelItem.setMaKhoa(ltc.getMaKhoa());
		modelItem.setSoSvToiThieu(ltc.getSoSvToiThieu());
		modelItem.setHuyLop(ltc.getHuyLop());

		return modelItem;
	}

	private LopTinChi createLopTinChiFromModelItem(LopTinChiModelItem modelItem) {
		LopTinChi ltc = LopTinChi.builder()
				.maLtc(modelItem.getMaLtc())
				.nienKhoa(modelItem.getNienKhoa())
				.hocKy(modelItem.getHocKy())
				.maMh(modelItem.getMaMh())
				.nhom(modelItem.getNhom())
				.maGv(modelItem.getMaGv())
				.maKhoa(modelItem.getMaKhoa())
				.soSvToiThieu(modelItem.getSoSvToiThieu())
				.huyLop(modelItem.getHuyLop())
				.build();
		return ltc;
	}
}
