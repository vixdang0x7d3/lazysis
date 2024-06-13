package com.lazygroup.lazysis.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
	private String ma_maSv;
	private String hoTen;
	private String nhom_lop;
	private Boolean isSv;
}
