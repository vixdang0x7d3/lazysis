package com.lazygroup.lazysis.lop;

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
public class LopDaoImpl implements Dao<Lop, String> {

	private final String sql_findAll = "SELECT * FROM LOP";
	private final String sql_findByMaLop = "SELECT * FROM LOP WHERE MALOP = :maLop";
	private final String sql_ThemLop = "{call sp_ThemLop(:maLop, :tenLop, :khoaHoc, :maKhoa, 1)}";
	private final String sql_GhiLop = "{call sp_GhiLop(:maLop, :tenLop, :khoaKhoc, 1)}";
	private final String sql_XoaLop = "{call sp_XoaLop(:maLop, 1)}";

	private final NamedParameterJdbcTemplate npJdbcTemplate;

	private final RowMapper<Lop> rowMapper = (rs, rowNum) -> {
		Lop lop = Lop.builder()
				.maLop(rs.getString("MALOP"))
				.tenLop(rs.getString("TENLOP"))
				.khoaHoc(rs.getString("KHOAHOC"))
				.maKhoa(rs.getString("MAKHOA"))
				.build();

		return lop;
	};

	@Autowired
	LopDaoImpl(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	@Override
	public List<Lop> list() {
		return npJdbcTemplate.query(sql_findAll, rowMapper);
	}

	@Override
	public void create(Lop t) {
		SqlParameterSource in = new BeanPropertySqlParameterSource(t);
		int row = npJdbcTemplate.update(sql_ThemLop, in);

		if (row == 1) {
			log.info("lop " + t.getMaLop() + " created");
		}
	}

	@Override
	public Optional<Lop> get(String id) {
		SqlParameterSource in = new MapSqlParameterSource("maLop", id);

		Lop lop = null;
		try {
			lop = npJdbcTemplate.queryForObject(sql_findByMaLop, in, rowMapper);
		} catch (DataAccessException e) {
			log.info("Lop not found " + id);
		}

		return Optional.ofNullable(lop);
	}

	@Override
	public void update(Lop t, String id) {
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("maLop", id)
				.addValue("tenLop", t.getTenLop())
				.addValue("khoaHoc", t.getKhoaHoc())
				.addValue("maKhoa", t.getMaKhoa());

		int row = npJdbcTemplate.update(sql_GhiLop, in);
		if (row == 1) {
			log.info("lop " + id + " updated");
		}
	}

	@Override
	public void delete(String id) {
		SqlParameterSource in = new MapSqlParameterSource("maSv", id);
		npJdbcTemplate.update(sql_XoaLop, in);

	}

}
