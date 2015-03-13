package com.premierinc.informatics.qmr.adminui;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * The Class AppConfig.
 * This class contains needed JavaConfig to initialize the application.
 * Supplemented by applicationContext.xml
 * 
 * @author crowland
 */
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@EnableBatchProcessing
public class AppConfig {

  /**
   * Setup view resolver.
   * All views should be located under /src/main/webapp/views.
   * All views need to be a jsp.
   *
   * @return the url based view resolver
   */
  @Bean
  public UrlBasedViewResolver setupViewResolver() {
    UrlBasedViewResolver resolver = new UrlBasedViewResolver();
    resolver.setPrefix("/views/");
    resolver.setSuffix(".jsp");
    resolver.setViewClass(JstlView.class);
    return resolver;
  }

  /**
   * Jpa vendor adapter.
   * Show SQL initialized to true.
   *
   * @return the hibernate jpa vendor adapter
   */
  @Bean
  public HibernateJpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    jpaVendorAdapter.setShowSql(true);
    return jpaVendorAdapter;
  }

  /**
   * Hibernate exception translator.
   *
   * @return the hibernate exception translator
   */
  @Bean
  public HibernateExceptionTranslator hibernateExceptionTranslator() {
    HibernateExceptionTranslator hibernateExceptionTranslator = new HibernateExceptionTranslator();
    return hibernateExceptionTranslator;
  }

  /**
   * Multipart File Resolver.
   * Allows the support of chunked file uploads.
   *
   * @return the commons multipart resolver
   */
  @Bean
  public CommonsMultipartResolver resolver() {
    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    resolver.setMaxUploadSize(-1);
    return resolver;
  }
}
