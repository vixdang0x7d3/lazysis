package com.lazygroup.lazysis.monhoc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonHoc {
	private String maMh;
	private String tenMh;
	private Integer soTietTh;
	private Integer soTietLt;
}
