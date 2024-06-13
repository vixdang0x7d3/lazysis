package com.lazygroup.lazysis.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.stereotype.Component;

import com.lazygroup.lazysis.login.LoginDto;
import com.lazygroup.lazysis.routing.ContextHolder;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DatabaseUtils {

	private final UserCredentialsDataSourceAdapter ucRoutingDataSource;
	private final String svUsername;
	private final String svPassword;

	@Autowired
	DatabaseUtils(UserCredentialsDataSourceAdapter ucRoutingDataSource,
			@Value("${db.defaults.sv-username}") String svUsername,
			@Value("${db.defaults.sv-password}") String svPassword) {

		this.svUsername = svUsername;
		this.svPassword = svPassword;
		this.ucRoutingDataSource = ucRoutingDataSource;
	}

	public Optional<LoginDto> validateLogin(String username, String password, String site, boolean isSv) {
		if (isSv) {

			String maSv = username;
			try {
				return Optional.of(spLayThongTinDangNhapSv(maSv, password, site));
			} catch (SQLException e) {
				log.error("validate login error", e);
				return Optional.empty();
			}

		} else {
			try {
				return Optional.of(spLayThongTinDangNhapGv(username, password, site));
			} catch (SQLException e) {
				log.error("validate login error", e);
				return Optional.empty();
			}
		}
	}

	private LoginDto spLayThongTinDangNhapGv(String username, String password, String site)
			throws SQLException {

		String sql = "{call sp_LayThongTinDangNhap_GV(?)}"; // query as default/helper login

		setThreadBoundContext(username, password, site);

		try (Connection conn = ucRoutingDataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return mapToGvLoginDto(rs);

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			clearThreadBoundContext();
		}
	}

	private LoginDto spLayThongTinDangNhapSv(String username, String password, String site)
			throws SQLException {

		String sql = "{call sp_LayThongTinDangNhap_SV(?, ?)}";

		setThreadBoundContext(svUsername, svPassword, site);

		try (Connection conn = ucRoutingDataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return mapToSvLoginDto(rs);

		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			clearThreadBoundContext();
		}
	}

	/* HELPER METHODS */

	public void setThreadBoundContext(String username, String password, String site) {
		ucRoutingDataSource.setCredentialsForCurrentThread(username, password);
		ContextHolder.set(site);
	}

	public void clearThreadBoundContext() {
		ucRoutingDataSource.removeCredentialsFromCurrentThread();
		ContextHolder.clear();
	}

	private LoginDto mapToSvLoginDto(ResultSet rs) throws SQLException {
		LoginDto svLoginDto = LoginDto.builder()
				.ma_maSv(rs.getString("MASV"))
				.hoTen(rs.getString("HOTEN"))
				.nhom_lop(rs.getString("MALOP"))
				.isSv(true)
				.build();

		return svLoginDto;
	}

	private LoginDto mapToGvLoginDto(ResultSet rs) throws SQLException {
		LoginDto gvLoginDto = LoginDto.builder()
				.ma_maSv(rs.getString("MA"))
				.hoTen(rs.getString("HOTEN"))
				.nhom_lop(rs.getString("TENNHOM"))
				.isSv(false)
				.build();

		return gvLoginDto;
	}
}
