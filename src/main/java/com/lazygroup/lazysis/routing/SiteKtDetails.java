package com.lazygroup.lazysis.routing;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "site-kt.datasource")
public class SiteKtDetails {
	private String id;
	private String username;
	private String url;
	private String driverClassName;
	private String password;
	private String name;
}
