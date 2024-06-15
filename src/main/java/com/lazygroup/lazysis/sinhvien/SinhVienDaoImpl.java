package com.lazygroup.lazysis.sinhvien;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import com.lazygroup.lazysis.dao.Dao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SinhVienDaoImpl implements Dao<SinhVien, String> {

	private final String sql_findAll = "SELECT * FROM SINHVIEN";
	private final String sql_ThongTinSinhVien = "{call sp_ThongTinSV(:maSv)}";

	private final String sql_ThemSinhVien = """
			{call sp_ThemSinhVien(:maSv, :ho, :ten, :phai, :diaChi, :ngaySinh, :maLop, :daNghiHoc, :password, 1)}
							""";

	private final String sql_SuaSinhVien = """
			{call sp_SuaSinhVien(:maSv, :ho, :ten, :phai, :diaChi, :ngaySinh, :maLop, :daNghiHoc, :password, 1)}
							""";

	private final String sql_XoaSinhVien = "{call sp_XoaSinhVien(:maSv, 1)}";

	private NamedParameterJdbcTemplate npJdbcTemplate;

	@Autowired
	SinhVienDaoImpl(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	RowMapper<SinhVien> rowMapper = (rs, rowNum) -> {

		Optional<Date> optionalNgaySinh = Optional.ofNullable(rs.getDate("NGAYSINH"));

		SinhVien sinhVien = SinhVien.builder()
				.maSv(rs.getString("MASV"))
				.ho(rs.getString("HO"))
				.ten(rs.getString("TEN"))
				.isFemale(rs.getBoolean("PHAI"))
				.diaChi(rs.getString("DIACHI"))
				.ngaySinh((optionalNgaySinh.isPresent()
						? optionalNgaySinh.get().toLocalDate().atStartOfDay()
						: null))
				.maLop(rs.getString("MALOP"))
				.daNghiHoc(rs.getBoolean("DANGHIHOC"))
				.build();

		return sinhVien;
	};

	@Override
	public List<SinhVien> list() {
		return npJdbcTemplate.query(sql_findAll, rowMapper);
	}

	@Override
	public void create(SinhVien t) {
		SqlParameterSource in = new BeanPropertySqlParameterSource(t);
		int row = npJdbcTemplate.update(sql_ThemSinhVien, in);

		if (row == 1) {
			log.info("sinhvien " + t.getMaSv() + " ");
		}

	}

	@Override
	public Optional<SinhVien> get(String id) {
		SqlParameterSource in = new MapSqlParameterSource("maSv", id);

		SinhVien sinhvien = null;
		try {
			sinhvien = npJdbcTemplate.queryForObject(sql_ThongTinSinhVien, in, rowMapper);
		} catch (DataAccessException e) {
			log.info("Sinhvien not found " + id);
		}

		return Optional.ofNullable(sinhvien);
	}

	@Override
	public void update(SinhVien t, String id) {
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("maSv", id)
				.addValue("ho", t.getHo())
				.addValue("ten", t.getTen())
				.addValue("phai", t.getIsFemale())
				.addValue("diaChi", t.getDiaChi())
				.addValue("ngaySinh", t.getNgaySinh())
				.addValue("maLop", t.getMaLop())
				.addValue("daNghiHoc", t.getDaNghiHoc())
				.addValue("password", t.getPassword());

		int row = npJdbcTemplate.update(sql_SuaSinhVien, in);
		if (row == 1) {
			log.info("sinhvien " + id + " updated");
		}
	}

	@Override
	public void delete(String id) {
		SqlParameterSource in = new MapSqlParameterSource().addValue("maSv", id);
		npJdbcTemplate.update(sql_XoaSinhVien, in);
	}

}
