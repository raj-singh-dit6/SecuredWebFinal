package com.securedweb.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@ComponentScan("com.secured.configuration")
@EnableJpaRepositories(
        entityManagerFactoryRef = "tenantEntityManager",
        transactionManagerRef = "tenantTransactionManager",
        basePackages = {"com.securedweb.repository"})
@PropertySource("classpath:application.properties")
public class TenantDatabaseConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(TenantDatabaseConfig.class);

   @Autowired
   private Environment springEnvironment;

   @Bean
   public JpaVendorAdapter jpaVendorAdapter() {
      return new HibernateJpaVendorAdapter();
   }

   @Bean(name = "tenantDataSource")
   @Primary
   public DataSource tenantDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(springEnvironment.getProperty("tenant.datasource.classname"));
      dataSource.setUrl(springEnvironment.getProperty("tenant.datasource.url"));
    		  //+ "?createDatabaseIfNotExist=true");
      dataSource.setUsername(springEnvironment.getProperty("tenant.datasource.user"));
      dataSource.setPassword(springEnvironment.getProperty("tenant.datasource.password"));
      return dataSource;
   }
   
   @Autowired
   @Bean(name = "tenantEntityManager")
   @Primary
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(
		    MultiTenantConnectionProvider multiTenantConnectionProvider,
		    CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {
      LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();

      emfBean.setDataSource(tenantDataSource());
      emfBean.setPackagesToScan("com.securedweb.model");
      emfBean.setJpaVendorAdapter(jpaVendorAdapter());
      Map<String, Object> properties = new HashMap<>();
      properties.put(org.hibernate.cfg.Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
      properties.put(org.hibernate.cfg.Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
      properties.put(org.hibernate.cfg.Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
      properties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
      properties.put("hibernate.dialect", springEnvironment.getProperty("hibernate.dialect"
              , "org.hibernate.dialect.MySQLDialect"));
      properties.put("hibernate.show_sql", springEnvironment.getProperty("hibernate.show_sql"
              , "true"));
      properties.put("hibernate.format_sql", springEnvironment.getProperty("hibernate.format_sql"
              , "true"));
      properties.put("hibernate.hbm2ddl.auto", springEnvironment.getProperty("hibernate.hbm2ddl.auto"
              , "update"));
      emfBean.setJpaPropertyMap(properties);
      emfBean.setPersistenceUnitName("tenant");
      return emfBean;
   }

   @Bean(name = "tenantTransactionManager")
   @Primary
   public JpaTransactionManager transactionManager(EntityManagerFactory tenantEntityManager) {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(tenantEntityManager);
      return transactionManager;
   }
}