package com.oneself.cloud.provider.dbcroe.datasource;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.alibaba.druid.pool.DruidDataSource;
import com.oneself.cloud.provider.dbcroe.dialect.impl.MysqlDialect;
import com.oneself.cloud.provider.dbcroe.interceptor.PaginationInterceptor;

/**
 * 数据源配置
 * 
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2016年12月27日上午10:43:00
 */
@Configuration
@PropertySource({ "classpath:application/druid.properties"})
public class DataSourceConfig {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	public DataSourceConfig() {
		logger.info("Loading Data source configuration to configure entry......");
	}

	/*
	 * 绑定资源属性
	 */
	@Value("${spring.datasource.type}")
	String type;

	@Value("${spring.datasource.driverClassName}")
	String driverClassName;

	@Value("${spring.datasource.url}")
	String url;

	@Value("${spring.datasource.username}")
	String username;

	@Value("${spring.datasource.password}")
	String password;

	@Value("${spring.datasource.initialSize}")
	String initialSize;

	@Value("${spring.datasource.minIdle}")
	String minIdle;

	@Value("${spring.datasource.maxActive}")
	String maxActive;

	@Value("${spring.datasource.maxWait}")
	String maxWait;

	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	String timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	String minEvictableIdleTimeMillis;

	@Value("${spring.datasource.validationQuery}")
	String validationQuery;

	@Value("${spring.datasource.testWhileIdle}")
	boolean testWhileIdle;

	@Value("${spring.datasource.testOnBorrow}")
	boolean testOnBorrow;

	@Value("${spring.datasource.testOnReturn}")
	boolean testOnReturn;

	@Value("${spring.datasource.poolPreparedStatements}")
	boolean poolPreparedStatements;

	@Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
	String maxPoolPreparedStatementPerConnectionSize;

	@Value("${spring.datasource.filters}")
	String filters;

	@Value("${spring.datasource.connectionProperties}")
	String connectionProperties;
	
	@Bean(name = "dataSourceRead")
	public DataSource dataSourceRead() {
		try {
			DruidDataSource druidSource = new DruidDataSource();
			druidSource.setDriverClassName(driverClassName);
			druidSource.setUrl(url);
			druidSource.setUsername(username);
			druidSource.setPassword(password);
			druidSource.setInitialSize(Integer.parseInt(initialSize.trim()));
			druidSource.setMinIdle(Integer.parseInt(minIdle.trim()));
			druidSource.setMaxActive(Integer.parseInt(maxActive.trim()));
			druidSource.setMaxWait(Integer.parseInt(maxWait.trim()));
			druidSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(timeBetweenEvictionRunsMillis.trim()));
			druidSource.setMinEvictableIdleTimeMillis(Integer.parseInt(minEvictableIdleTimeMillis.trim()));
			druidSource.setValidationQuery(validationQuery);
			druidSource.setTestWhileIdle(testWhileIdle);
			druidSource.setTestOnBorrow(testOnBorrow);
			druidSource.setTestOnReturn(testOnReturn);
			druidSource.setPoolPreparedStatements(poolPreparedStatements);
			druidSource.setMaxPoolPreparedStatementPerConnectionSize(
					Integer.parseInt(maxPoolPreparedStatementPerConnectionSize.trim()));
			druidSource.setFilters(filters);
			druidSource.setConnectionProperties(connectionProperties);
			return druidSource;
		} catch (Exception e) {
			logger.error("数据源加载失败......");
			e.printStackTrace();
		}
		return null;
	}

	/*@Bean(name = "dataSourceWrite")
	public DataSource dataSourceWrite() {
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}*/

	@Bean(name = "dbDialect")
	public MysqlDialect dbDialect() {
		return new MysqlDialect();
	}

	@Bean(name = "paginationInterceptor")
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		paginationInterceptor.setDialect(dbDialect());
		return paginationInterceptor;
	}
}
