package com.lazygroup.lazysis.dangky;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DangKy {
	private Integer MaLtc;
	private String MaSV;
	private Integer diem_cc;
	private Float diem_gk;
	private Float diem_ck;
}
