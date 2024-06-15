import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.dao.Dao;
import com.lazygroup.lazysis.util.DatabaseUtils;

@Component
public class SinhVienInteractor {

	SinhVienModel model;

	Dao<SinhVien, String> dao;
	DatabaseUtils dbUtils;

	@Autowired
	SinhVienInteractor(SinhVienModel model, SinhVienDaoImpl sinhvienDao,
			DatabaseUtils dbUtils) {
		this.model = model;
		this.dao = sinhvienDao;
		this.dbUtils = dbUtils;

	}

	public List<SinhVienModelItem> fetchData() {

		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		List<SinhVienModelItem> data = dao.list().stream().map((sv) -> createModelItemFromSinhVien(sv))
				.collect(Collectors.toList());

		dbUtils.clearThreadBoundContext();

		return data;
	}

	public void themSinhVien(SinhVienModelItem modelItem) {
		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		dao.create(createSinhVienFromModelItem(modelItem));

		dbUtils.clearThreadBoundContext();
	}

	public void suaSinhVien(SinhVienModelItem modelItem) {
		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		dao.update(createSinhVienFromModelItem(modelItem), model.getSelectedItem().getMaSv());

		dbUtils.clearThreadBoundContext();
	}

	public void xoaSinhVien() {
		dbUtils.setThreadBoundContext(model.getUsername(), model.getPassword(), model.getSite());

		dao.delete(model.getSelectedItem().getMaLop());

		dbUtils.clearThreadBoundContext();
	}

	public void updateModel(List<SinhVienModelItem> data) {
		model.getAllItems().setAll(data);
	}

	public void updateModel(SinhVienModelItem data) {
		model.allItemsProperty().add(data);
	}

	public void updateModel(String maSv, SinhVienModelItem data) {
		Optional<SinhVienModelItem> oldItem = getSinhVienModelItem(maSv);
		oldItem.ifPresent(model.getAllItems()::remove);

		updateModel(data);
	}

	public void updateModel(String maSv) {
		Optional<SinhVienModelItem> item = getSinhVienModelItem(maSv);
		item.ifPresent(model.getAllItems()::remove);
	}

	public Optional<SinhVienModelItem> getSinhVienModelItem(String maSv) {

		for (SinhVienModelItem i : model.getAllItems()) {
			if (i.getMaSv().equals(maSv))
				return Optional.of(i);
		}
		return Optional.empty();
	}

	/* MAPPERS */

	public SinhVienModelItem createModelItemFromSinhVien(SinhVien sinhvien) {
		SinhVienModelItem modelItem = new SinhVienModelItem();

		modelItem.setMaSv(sinhvien.getMaSv());
		modelItem.setHo(sinhvien.getHo());
		modelItem.setTen(sinhvien.getTen());
		modelItem.setDiaChi(sinhvien.getDiaChi());
		modelItem.setIsFemale(sinhvien.getIsFemale());
		modelItem.setDiaChi(sinhvien.getDiaChi());
		modelItem.setNgaySinh(sinhvien.getNgaySinh());
		modelItem.setMaLop(sinhvien.getMaLop());
		modelItem.setDaNghiHoc(sinhvien.getDaNghiHoc());
		modelItem.setPassword(sinhvien.getPassword());

		return modelItem;
	}

	public SinhVien createSinhVienFromModelItem(SinhVienModelItem modelItem) {

		SinhVien sinhvien = SinhVien.builder()
				.maSv(modelItem.getMaSv())
				.ho(modelItem.getHo())
				.ten(modelItem.getTen())
				.isFemale(modelItem.getIsFemale())
				.diaChi(modelItem.getDiaChi())
				.ngaySinh(modelItem.getNgaySinh())
				.maLop(modelItem.getMaLop())
				.daNghiHoc(modelItem.getDaNghiHoc())
				.password(modelItem.getPassword())
				.build();

		return sinhvien;
	}
}
