package com.lazygroup.lazysis.loptinchi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LopTinChi {
	Integer maLtc;
	String nienKhoa;
	Integer hocKy;
	String maMh;
	Integer nhom;
	String maGv;
	String maKhoa;
	Integer soSvToiThieu;
	Boolean huyLop;
}
