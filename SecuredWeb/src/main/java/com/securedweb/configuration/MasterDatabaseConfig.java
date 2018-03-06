package com.securedweb.configuration;

//@ComponentScan("com.secured")
/*@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.securedweb.master.repository",
        entityManagerFactoryRef = "masterEntityManager",
        transactionManagerRef = "masterTransactionManager"
)
@PropertySource("classpath:application.properties")
*/public class MasterDatabaseConfig {
/*	
	private static final Logger LOG = LoggerFactory.getLogger(MasterDatabaseConfig.class);
 
	 @Autowired
	   private Environment springEnvironment;

	   @Bean(name="masterDataSource")
	   public DataSource masterDataSource() {
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName(springEnvironment.getProperty("master.datasource.classname"));
	      dataSource.setUrl(springEnvironment.getProperty("master.datasource.url") + "?createDatabaseIfNotExist=true");
	      dataSource.setUsername(springEnvironment.getProperty("master.datasource.user"));
	      dataSource.setPassword(springEnvironment.getProperty("master.datasource.password"));
	      return dataSource;
	   }

	   @Bean(name = "masterEntityManager")
	   @Primary
	   public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	      entityManagerFactoryBean.setDataSource(masterDataSource());
	      entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
	      entityManagerFactoryBean.setPackagesToScan(new String[]{"com.securedweb.master.model"});
	      entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
	      entityManagerFactoryBean.setPersistenceUnitName("master");
	      return entityManagerFactoryBean;
	   }

	   private Properties getHibernateProperties() {
	      Properties properties = new Properties();
	      properties.put("hibernate.dialect", springEnvironment.getProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect"));
	      properties.put("hibernate.show_sql", springEnvironment.getProperty("hibernate.show_sql", "true"));
	      properties.put("hibernate.format_sql", springEnvironment.getProperty("hibernate.format_sql", "true"));
	      properties.put("hibernate.hbm2ddl.auto", springEnvironment.getProperty("hibernate.hbm2ddl.auto", "update"));
	      return properties;
	   }

	   @Bean(name = "masterTransactionManager")
	   public JpaTransactionManager transactionManager(EntityManagerFactory masterEntityManager) {
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(masterEntityManager);
	      return transactionManager; 
	   }

*/	}
