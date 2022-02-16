package com.example.configDB;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "authEntityManagerFactory",
        transactionManagerRef = "authTransactionManager",
        basePackages = {"com.example.auth"})
@EnableTransactionManagement
public class AuthConfig {

    @Bean("authDataSource")
    @ConfigurationProperties(prefix = "auth.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "authEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean csiEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("authDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.auth")
                .persistenceUnit("postgres")
                .build();
    }
    @Bean(name = "authTransactionManager")
    public PlatformTransactionManager authTransactionManager(
            @Qualifier("authEntityManagerFactory") EntityManagerFactory authEntityManagerFactory) {
        return new JpaTransactionManager(authEntityManagerFactory);
    }
}
