package com.oneself.cloud.provider.dbcroe.datasource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.oneself.cloud.provider.dbcroe.base.impl.DefaultService;

/**
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年4月10日下午1:25:31
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(MyBatisConfiguration.class);

	public MyBatisConfiguration() {
		logger.info("Loading mybatis to configure entry......");
	}
	
	@Resource(name = "dataSourceRead")
	DataSource dataSourceRead;
	
	@Resource(name = "paginationInterceptor")
	Interceptor[] paginationInterceptor;
	
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSourceRead);
		//bean.setTypeAliasesPackage("org.springmvc.oneself.*.model");
		// 添加XML目录
		ResourceLoader resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setConfigLocation(resolver.getResource("sqlmap/sql-map-config.xml"));
			bean.setPlugins(paginationInterceptor);
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Bean(name = "sqlSession")
	public SqlSessionTemplate sqlSession(){
		return new SqlSessionTemplate(sqlSessionFactoryBean());
	}
	
	@Bean(name = "defaultService")
	public DefaultService defaultService(){
		SqlSession session=sqlSession();
		DefaultService defaultService=new DefaultService();
		defaultService.setSession(session);
		return defaultService;
	}
	
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSourceRead);
	}
	
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager(){
		return new DataSourceTransactionManager(dataSourceRead);
	}
}
