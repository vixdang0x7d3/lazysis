package com.lazygroup.lazysis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lazygroup.lazysis.sinhvien.SinhVienModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Configuration
public class DsSinhVienModelConfiguration {

	@Bean
	ObservableList<SinhVienModel> dsSinhVienModel() {
		ObservableList<SinhVienModel> model = FXCollections.observableArrayList();
		return model;
	}

}
