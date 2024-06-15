package com.lazygroup.lazysis.DangKy;

import com.lazygroup.lazysis.dao.Dao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DangKyDaoImpl implements Dao<DangKy, Object[]> {

	private String sql_findAll = "SELECT * FROM DANGKY";

	private String sql_DangKyLTC = "{call sp_DangKyLTC(:MaSV, :MaLTC)}";

	private String sql_huyDangKy = "{call sp_huyDangKy(:MaSV, :MaLTC)}";

	private String sql_updateDiem = "{call sp_CapNhatDiem(:BangDiem)}";

	private NamedParameterJdbcTemplate npJdbcTemplate;

	@Autowired
	public DangKyDaoImpl(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	RowMapper<DangKy> rowMapper = (rs, rowNum) -> {
		return DangKy.builder()
				.MaLtc(rs.getInt("MaLTC"))
				.MaSV(rs.getString("MaSV"))
				.diem_cc(rs.getInt("Diem_CC"))
				.diem_gk(rs.getFloat("Diem_GK"))
				.diem_ck(rs.getFloat("Diem_Ck"))
				.build();
	};

	@Override
	public List<DangKy> list() {
		return npJdbcTemplate.query(sql_findAll, rowMapper);
	}

	@Override
	public void create(DangKy dangKy) {
		SqlParameterSource params = new 

		int row = npJdbcTemplate.update(sql_DangKyLTC, params);

		if (row == 1) {
			log.info("Created LopTinChi with id: {} {}", dangKy.getMaLtc(), dangKy.getMaSV());
		}
	}

	@Override
	public Optional<DangKy> get(Object[] id) {
		return Optional.empty();
	}

	@Override
	public void update(DangKy dangKy, Object[] id) {
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("MaLTC", dangKy.getMaLtc())
				.addValue("MaSV", dangKy.getMaSV())
				.addValue("Diem_CC", dangKy.getDiem_cc())
				.addValue("Diem_GK", dangKy.getDiem_gk())
				.addValue("Diem_CK", dangKy.getDiem_ck());
		int row = npJdbcTemplate.update(sql_updateDiem, in);
		if (row == 1) {
			log.info("lopTinChi " + id + " updated");
		}
	}

	@Override
	public void delete(Object[] id) {
	}
}
