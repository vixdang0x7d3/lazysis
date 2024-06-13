package com.lazygroup.lazysis.sinhvien;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SinhVien {
	private String maSv;
	private String ho;
	private String ten;
	private Boolean isFemale;
	private String diaChi;
	private LocalDateTime ngaySinh;
	private String maLop;
	private Boolean daNghiHoc;
	private String password;
}
