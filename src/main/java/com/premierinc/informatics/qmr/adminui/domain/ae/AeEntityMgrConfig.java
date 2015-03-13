package com.premierinc.informatics.qmr.adminui.domain.ae;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The Class AeEntityMgrConfig. Configuration for the AE datasource and Repository.
 *
 * @author crowland
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "aeEntityManagerFactory",
    transactionManagerRef = "aeTransactionManager",
    basePackages = { "com.premierinc.informatics.qmr.adminui.domain.ae" })
public class AeEntityMgrConfig {

  @Autowired
  JpaVendorAdapter jpaVendorAdapter;

  /**
   * Data Source. The JavaConfig for the AE datasource.
   *
   * @return the data source
   */
  @Bean(name = "aeDataSource")
  public DataSource dataSource() {
    final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
    dsLookup.setResourceRef(true);
    DataSource dataSource = dsLookup.getDataSource("java:jboss/AdvisorExchangeDS");
    return dataSource;
  }

  /**
   * Entity manager.
   *
   * @return the entity manager
   */
  @Bean(name = "aeEntityManager")
  public EntityManager entityManager() {
    return entityManagerFactory().createEntityManager();
  }

  /**
   * Entity manager factory.
   *
   * @return the entity manager factory
   */
  @Bean(name = "aeEntityManagerFactory")
  public EntityManagerFactory entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
    lef.setDataSource(dataSource());
    lef.setJpaVendorAdapter(jpaVendorAdapter);
    lef.setPackagesToScan("com.premierinc.informatics.qmr.adminui.domain.ae");
    lef.setPersistenceUnitName("aePersistenceUnit");
    lef.afterPropertiesSet();
    return lef.getObject();
  }

  /**
   * Transaction manager.
   *
   * @return the platform transaction manager
   */
  @Bean(name = "aeTransactionManager")
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager(entityManagerFactory());
  }

  /**
   * Jdbc template.
   *
   * @param dataSource the data source
   * @return the jdbc template
   */
  @Bean(name = "aeJdbcTemplate")
  public JdbcTemplate jdbcTemplate(@Qualifier("aeDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

}
