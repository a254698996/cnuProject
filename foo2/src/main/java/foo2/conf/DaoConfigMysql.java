/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.config.DaoConfig.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年9月3日 下午2:55:14 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年9月3日    |    Administrator     |     Created 
 */
package foo2.conf;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import foo2.dao.hibernate.impl.CP_Hibernate4DAOImpl;
 

/**
 * Description: <数据源相关bean的注册>. <br>
 * <p>
 * <使用说明>
 * </p>
 * Makedate:2014年9月3日 下午2:55:14
 * 
 * @author Administrator
 * @version V1.0
 */
@Configuration
// 启用注解事务管理，使用CGLib代理
@EnableTransactionManagement(proxyTargetClass = true)
@Import({ DataSourceConfigMysql.class })
public class DaoConfigMysql {

	private static final Logger logger = LoggerFactory.getLogger(DaoConfigMysql.class);

	@Value("${hibernate.dialect}")
	String hibernate_dialect;
	@Value("${hibernate.show_sql}")
	String hibernate_show_sql;

	/**
	 * 描述 : <负责解析资源文件>. <br>
	 * <p>
	 * <这个类必须有，而且必须声明为static，否则不能正常解析>
	 * </p>
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer placehodlerConfigurer() {
		logger.info("PropertySourcesPlaceholderConfigurer");
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Resource(name = "dataSource")
	public DataSource dataSource;

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean mysqlLocalSessionFactoryBean() {
		logger.info("sessionFactory");
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		String[] packagesToScan = new String[] { "foo2.web.function.**.model" };
		sessionFactory.setPackagesToScan(packagesToScan);

		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", hibernate_dialect);
		hibernateProperties.setProperty("hibernate.show_sql", hibernate_show_sql);
		hibernateProperties.setProperty("hibernate.current_session_context_class",
				"org.springframework.orm.hibernate4.SpringSessionContext");
		sessionFactory.setHibernateProperties(hibernateProperties);

		return sessionFactory;

	}

	@Bean(name = "hibernateDAO")
	public CP_Hibernate4DAOImpl hibernate4Dao() {
		logger.info("hibernateDAO");
		CP_Hibernate4DAOImpl dao = new CP_Hibernate4DAOImpl();
		dao.setSessionFactory(mysqlLocalSessionFactoryBean().getObject());
		return dao;
	}

	@Bean(name = "transactionManager")
	public HibernateTransactionManager hibernateTransactionManager() {
		logger.info("transactionManager");
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(mysqlLocalSessionFactoryBean().getObject());
		return hibernateTransactionManager;
	}
}
