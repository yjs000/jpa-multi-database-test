package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.domain.bis",
        entityManagerFactoryRef = "bisEntityManager",
        transactionManagerRef = "bisTransactionManager")
public class BisDBConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean bisEntityManager() {
        LocalContainerEntityManagerFactoryBean factory
                = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(bisDataSource());
        factory.setPackagesToScan("com.example.demo.domain.bis");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.getJpaPropertyMap().put("hibernate.dialect"
                , "org.hibernate.dialect.Oracle10gDialect");
        return factory;
    }

    @Bean
    @ConfigurationProperties(prefix="spring.second-datasource")
    public DataSource bisDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager bisTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(bisEntityManager().getObject());
        return transactionManager;
    }

}