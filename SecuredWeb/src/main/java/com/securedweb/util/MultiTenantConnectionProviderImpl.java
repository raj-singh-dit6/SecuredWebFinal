package com.securedweb.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;

import com.securedweb.dto.master.TenantDTO;
import com.securedweb.model.master.Tenant;
import com.securedweb.model.master.TenantDBDataSource;
import com.securedweb.service.master.TenantDBDataSourceService;
import com.securedweb.service.master.TenantService;

@Component("multiTenantConnectionProviderImpl")
@SuppressWarnings("serial")
@PropertySource("classpath:application.properties")
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl implements ApplicationListener<ContextRefreshedEvent>,ServiceRegistryAwareService {
	@Autowired
	private Environment springEnvironment;

	@Autowired
	TenantService tenantService;
	   
	@Autowired
    TenantDBDataSourceService tenantDBDataSourceService;

	@Autowired
	@Qualifier("tenantDataSource")
	DataSource dataSource;

	private final Map<String, DataSource> map = new HashMap<>();

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		init();
	}
	
	public void init() {
		   List<TenantDTO> tenants = tenantService.getAllTenants();
		      System.out.println("----INITITALIZING TENANTS FROM DB ------------");
		      System.out.println(tenants.size());
		      for (TenantDTO tenantDTO : tenants) {
		    	  TenantDBDataSource tenantDBDataSource = tenantDBDataSourceService.findById(tenantDTO.getTenantId()); 
		          map.put(tenantDTO.getTenantId().toString(),constructDataSource(tenantDBDataSource) );
		      }
	   }
	
	private DataSource constructDataSource(TenantDBDataSource tenantDBDataSource) {
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName(tenantDBDataSource.getDbDriver());
	      dataSource.setUrl(tenantDBDataSource.getDbHost()+tenantDBDataSource.getDbName()+"?createDatabaseIfNotExist=true");
	      dataSource.setUsername(tenantDBDataSource.getDbUser());
	      dataSource.setPassword(tenantDBDataSource.getDbPassword());
	      //dynamicEntityManagerFactory(dataSource);
	      return dataSource;
	 }
	
	private DataSource constructDataSource(String dbName) {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(springEnvironment.getProperty("tenant.datasource.classname"));
			dataSource.setUrl(springEnvironment.getProperty("tenant.datasource.url") + dbName+ "?createDatabaseIfNotExist=true");
			dataSource.setUsername(springEnvironment.getProperty("tenant.datasource.user"));
			dataSource.setPassword(springEnvironment.getProperty("tenant.datasource.password"));
			//dynamicEntityManagerFactory(dataSource);
			return dataSource;
   }
	   

	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		Map lSettings = serviceRegistry.getService(ConfigurationService.class).getSettings();
		DataSource localDs = (DataSource) lSettings.get("hibernate.connection.datasource");
		dataSource = localDs;
	}


	public LocalContainerEntityManagerFactoryBean dynamicEntityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setPackagesToScan(new String[] { "com.securedweb.model.master" });
		entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
		entityManagerFactoryBean.setPersistenceUnitName(dataSource.toString());
		entityManagerFactoryBean.afterPropertiesSet();
		return entityManagerFactoryBean;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect",
				springEnvironment.getProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"));
		properties.put("hibernate.show_sql", springEnvironment.getProperty("hibernate.show_sql", "true"));
		properties.put("hibernate.format_sql", springEnvironment.getProperty("hibernate.format_sql", "true"));
		properties.put("hibernate.hbm2ddl.auto", springEnvironment.getProperty("hibernate.hbm2ddl.auto", "update"));
		properties.put("hibernate.id.new_generator_mappings", springEnvironment.getProperty("hibernate.id.new_generator_mappings", "false"));
		return properties;
	}

	@Override
	protected DataSource selectAnyDataSource() {
		System.out.println("MultiTenantConnectionProvider : DEFAULT");
		/*return map.get("1")==null?dataSource:map.get("1");*/
		return dataSource;
	}

	@Override
	protected DataSource selectDataSource(String key) {
		System.err.println("MultiTenantConnectionProvider : "+key);
		return map.get(key);
	}

	public void addTenant(String tenantKey) {
		map.put(tenantKey, constructDataSource(tenantKey));
	}
		
}
	   
