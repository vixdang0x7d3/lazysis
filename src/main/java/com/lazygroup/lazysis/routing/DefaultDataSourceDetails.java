package com.lazygroup.lazysis.routing;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "default.datasource")
public class DefaultDataSourceDetails {
	private String username;
	private String url;
	private String driverClassName;
	private String password;
}
