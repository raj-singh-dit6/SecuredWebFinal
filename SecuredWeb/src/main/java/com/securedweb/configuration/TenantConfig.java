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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
@ComponentScan("com.securedweb.util")
@EnableJpaRepositories(
        entityManagerFactoryRef = "tenantEntityManager",
        transactionManagerRef = "tenantTransactionManager",
        basePackages = {"com.securedweb.repository.tenant"})
@PropertySource("classpath:application.properties")
public class TenantConfig {

	private static final Logger LOG = LoggerFactory.getLogger(TenantConfig.class);
	
   @Autowired
   private Environment springEnvironment;
   
   @Bean
   public JpaVendorAdapter jpaVendorAdapter() {
      return new HibernateJpaVendorAdapter();
   }
   
   @Bean(name= "tenantDataSource")
   public DataSource tenantDataSource() {
	   
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(springEnvironment.getProperty("tenant.database.classname",
              "com.mysql.jdbc.Driver"));
      dataSource.setUrl(springEnvironment.getProperty("tenant.database.url", 
              "jdbc:mysql://localhost:3306/multi_tenant_db") + "?createDatabaseIfNotExist=true&useUnicode=yes&characterEncoding=UTF-8");
      dataSource.setUsername(springEnvironment.getProperty("tenant.database.user", "root"));
      dataSource.setPassword(springEnvironment.getProperty("tenant.database.password", "admin"));
      return dataSource;
   }

   @Bean(name = "tenantEntityManager")
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(
								           MultiTenantConnectionProvider connectionProvider,
								           CurrentTenantIdentifierResolver tenantResolver) {
      
	  LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
      
      emfBean.setDataSource(tenantDataSource());
      emfBean.setPackagesToScan("com.securedweb.model.tenant");
      emfBean.setJpaVendorAdapter(jpaVendorAdapter());
      
      Map<String, Object> properties = new HashMap<>();
      properties.put(org.hibernate.cfg.Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
      properties.put(org.hibernate.cfg.Environment.MULTI_TENANT_CONNECTION_PROVIDER, connectionProvider);
      properties.put(org.hibernate.cfg.Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantResolver);
      properties.put("hibernate.dialect", springEnvironment.getProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect"));
      /*properties.put("hibernate.dialect.storage_engine",springEnvironment.getProperty("hibernate.dialect.storage_engine","innodb"));*/
      properties.put("hibernate.show_sql", springEnvironment.getProperty("hibernate.show_sql", "true"));
      properties.put("hibernate.format_sql", springEnvironment.getProperty("hibernate.format_sql", "true"));
      properties.put("hibernate.hbm2ddl.auto", springEnvironment.getProperty("hibernate.hbm2ddl.auto", "update"));
      properties.put("hibernate.id.new_generator_mappings", springEnvironment.getProperty("hibernate.id.new_generator_mappings", "false"));
      properties.put("hibernate.physical_naming_strategy", "com.securedweb.configuration.CustomNamingStrategy");
      
      emfBean.setJpaPropertyMap(properties);
      emfBean.setPersistenceUnitName("tenant");
      
      return emfBean;
   }

   @Bean(name = "tenantTransactionManager")
   public JpaTransactionManager transactionManager(EntityManagerFactory tenantEntityManager/*,DataSource dataSource*/) {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(tenantEntityManager);
      //transactionManager.setDataSource(tenantDataSource());
      return transactionManager;
   }
}