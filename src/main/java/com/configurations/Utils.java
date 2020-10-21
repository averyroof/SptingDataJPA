package com.configurations;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.repositories")
public class Utils {

    private final String PROPERTY_DRIVER = "org.postgresql.Driver";
    private final String PROPERTY_URL = "jdbc:postgresql://localhost:5432/usermanagment";
    private final String PROPERTY_USERNAME = "postgres";
    private final String PROPERTY_PASSWORD = "123";

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
        lfb.setDataSource(dataSource());
        lfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        lfb.setPackagesToScan("com.models");
        return lfb;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(PROPERTY_URL);
        ds.setUsername(PROPERTY_USERNAME);
        ds.setPassword(PROPERTY_PASSWORD);
        ds.setDriverClassName(PROPERTY_DRIVER);
        return ds;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
