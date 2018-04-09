package com.securedweb.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(
        basePackages = "com.securedweb.repository.master",
        entityManagerFactoryRef = "masterEntityManager",
        transactionManagerRef = "masterTransactionManager"
)
@PropertySource("classpath:application.properties")
public class MasterConfig {

  private static final Logger LOG = LoggerFactory.getLogger(MasterConfig.class);
	
   @Autowired
   private Environment springEnvironment;

   @Bean(name= "masterDataSource")
   public DataSource masterDataSource() {
	  
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(springEnvironment.getProperty("master.database.classname",
              "com.mysql.jdbc.Driver"));
      dataSource.setUrl(springEnvironment.getProperty("master.database.url", 
              "jdbc:mysql://localhost:3306/master_db") + "?createDatabaseIfNotExist=true&useUnicode=yes&characterEncoding=UTF-8");
      dataSource.setUsername(springEnvironment.getProperty("master.database.user", "root"));
      dataSource.setPassword(springEnvironment.getProperty("master.database.password", "admin"));
      return dataSource;
   }

   @Bean(name = "masterEntityManager")
   public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory() {
      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean 
              = new LocalContainerEntityManagerFactoryBean();
      entityManagerFactoryBean.setDataSource(masterDataSource());
      entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
      entityManagerFactoryBean.setPackagesToScan(new String[]{"com.securedweb.model.master"});
      entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
      entityManagerFactoryBean.setPersistenceUnitName("master");
      return entityManagerFactoryBean;
   }

   private Properties getHibernateProperties() {
      Properties properties = new Properties();
      properties.put("hibernate.dialect", springEnvironment.getProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect"));
      /*properties.put("hibernate.dialect.storage_engine",springEnvironment.getProperty("hibernate.dialect.storage_engine","innodb"));*/
      properties.put("hibernate.show_sql", springEnvironment.getProperty("hibernate.show_sql", "true"));
      properties.put("hibernate.format_sql", springEnvironment.getProperty("hibernate.format_sql", "true"));
      properties.put("hibernate.hbm2ddl.auto", springEnvironment.getProperty("hibernate.hbm2ddl.auto", "update"));
      properties.put("hibernate.id.new_generator_mappings", springEnvironment.getProperty("hibernate.id.new_generator_mappings", "false"));
      properties.put("hibernate.physical_naming_strategy", "com.securedweb.configuration.CustomNamingStrategy");
      
      
      return properties;
   }

   @Bean(name = "masterTransactionManager")
   @Primary
   public JpaTransactionManager transactionManager(EntityManagerFactory masterEntityManager/*, DataSource dataSource*/) {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(masterEntityManager);
      //transactionManager.setDataSource(masterDataSource());
      return transactionManager;
   }

}
