package com.lazygroup.lazysis.lop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lop {
	private String maLop;
	private String tenLop;
	private String khoaHoc;
	private String maKhoa;
}
