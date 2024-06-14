package com.lazygroup.lazysis.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.lazygroup.lazysis.routing.ContextHolder;
import com.lazygroup.lazysis.routing.DefaultDataSourceDetails;
import com.lazygroup.lazysis.routing.SiteCnttDetails;
import com.lazygroup.lazysis.routing.SiteKtDetails;
import com.lazygroup.lazysis.routing.SiteVtDetails;

@Configuration
public class RoutingDataSourceConfiguration {

	@Autowired
	private SiteCnttDetails siteCnttDetails;
	@Autowired
	private SiteVtDetails siteVtDetails;
	@Autowired
	private SiteKtDetails siteKtDetails;
	@Autowired
	private DefaultDataSourceDetails defaultDataSourceDetails;

	@Bean("siteIdLookUpMap")
	public Map<String, String> siteIdLookUpMap() {
		Map<String, String> idMap = new HashMap<>();

		idMap.put(siteCnttDetails.getName(), siteCnttDetails.getId());
		idMap.put(siteVtDetails.getName(), siteVtDetails.getId());
		idMap.put(siteKtDetails.getName(), siteKtDetails.getId());

		return idMap;
	}

	@Bean("siteIdLookUpMapGV")
	public Map<String, String> siteIdLookUpMapGV() {
		Map<String, String> idMap = new HashMap<>();

		idMap.put(siteCnttDetails.getName(), siteCnttDetails.getId());
		idMap.put(siteVtDetails.getName(), siteVtDetails.getId());
		return idMap;
	}

	@Bean("siteUrlLookUpMap")
	public Map<String, String> siteUrlLookUpMap() {
		Map<String, String> nameMap = new HashMap<>();

		nameMap.put(siteCnttDetails.getId(), siteCnttDetails.getUrl());
		nameMap.put(siteVtDetails.getId(), siteVtDetails.getUrl());
		nameMap.put(siteKtDetails.getId(), siteKtDetails.getUrl());

		return nameMap;
	}

	@Bean("ucRoutingDataSource")
	public UserCredentialsDataSourceAdapter getUCRoutingDataSource() {
		UserCredentialsDataSourceAdapter adapter = new UserCredentialsDataSourceAdapter();
		adapter.setTargetDataSource(routingDataSource());
		return adapter; // when not setCredentialsForCurrentThread, fallback to default credential
	}

	private DataSource routingDataSource() {

		AbstractRoutingDataSource routingDatasource = new AbstractRoutingDataSource() {

			@Override
			protected Object determineCurrentLookupKey() {
				return ContextHolder.get();
			}
		};

		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(siteCnttDetails.getId(), siteCnttDataSource());
		targetDataSources.put(siteVtDetails.getId(), siteVtDataSource());
		targetDataSources.put(siteKtDetails.getId(), siteKtDataSource());

		routingDatasource.setTargetDataSources(targetDataSources);
		routingDatasource.setDefaultTargetDataSource(defaultDataSource());
		routingDatasource.afterPropertiesSet();

		return routingDatasource;
	}

	private DataSource siteCnttDataSource() {

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUsername(siteCnttDetails.getUsername());
		ds.setUrl(siteCnttDetails.getUrl());
		ds.setDriverClassName(siteCnttDetails.getDriverClassName());
		ds.setPassword(siteCnttDetails.getPassword());

		return ds;

		// return DataSourceBuilder.create()
		// .username(siteCnttDetails.getUsername())
		// .url(siteCnttDetails.getUrl())
		// .driverClassName(siteCnttDetails.getDriverClassName())
		// .password(siteCnttDetails.getPassword())
		// .build();
	}

	private DataSource siteVtDataSource() {

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUsername(siteVtDetails.getUsername());
		ds.setUrl(siteVtDetails.getUrl());
		ds.setDriverClassName(siteVtDetails.getDriverClassName());
		ds.setPassword(siteVtDetails.getPassword());

		return ds;

		// return DataSourceBuilder.create()
		// .username(siteVtDetails.getUsername())
		// .url(siteVtDetails.getUrl())
		// .driverClassName(siteVtDetails.getDriverClassName())
		// .password(siteVtDetails.getPassword())
		// .build();
	}

	private DataSource siteKtDataSource() {

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUsername(siteKtDetails.getUsername());
		ds.setUrl(siteKtDetails.getUrl());
		ds.setDriverClassName(siteKtDetails.getDriverClassName());
		ds.setPassword(siteKtDetails.getPassword());

		return ds;

		// return DataSourceBuilder.create()
		// .username(siteKtDetails.getUsername())
		// .url(siteKtDetails.getUrl())
		// .driverClassName(siteKtDetails.getDriverClassName())
		// .password(siteKtDetails.getPassword())
		// .build();
	}

	private DataSource defaultDataSource() {

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUsername(defaultDataSourceDetails.getUsername());
		ds.setUrl(defaultDataSourceDetails.getUrl());
		ds.setDriverClassName(defaultDataSourceDetails.getDriverClassName());
		ds.setPassword(defaultDataSourceDetails.getPassword());

		return ds;

		// return DataSourceBuilder.create()
		// .username(defaultDataSourceDetails.getUsername())
		// .url(defaultDataSourceDetails.getUrl())
		// .driverClassName(defaultDataSourceDetails.getDriverClassName())
		// .password(defaultDataSourceDetails.getPassword())
		// .build();
	}
}
