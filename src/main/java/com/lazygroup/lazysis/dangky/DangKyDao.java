package com.lazygroup.lazysis.dangky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DangKyDao {

	private final String sql_DangKyLTC = "{call sp_DangKyLtc(:maSv, :MaLTC)}";

	private final String sql_huyDangKy = "{call sp_HuyDangKy(:MaSv, :MaLTC)}";

	private final String sql_updateDiem = "{call sp_CapNhatDiem(:BangDiem)}";

	private final NamedParameterJdbcTemplate npJdbcTemplate;

	private final RowMapper<DangKy> rowMapper = (rs, rowNum) -> {
		return DangKy.builder()
				.MaLtc(rs.getInt("MALTC"))
				.MaSV(rs.getString("MaSV"))
				.diem_cc(rs.getInt("Diem_CC"))
				.diem_gk(rs.getFloat("Diem_GK"))
				.diem_ck(rs.getFloat("Diem_Ck"))
				.build();
	};

	@Autowired
	DangKyDao(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	;wq



}
