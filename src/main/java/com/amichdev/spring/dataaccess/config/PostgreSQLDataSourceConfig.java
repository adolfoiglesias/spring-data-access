package com.amichdev.spring.dataaccess.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.amichdev.spring.dataaccess.course",
        entityManagerFactoryRef = "postgresqlEntityManagerFactory",
        transactionManagerRef = "postgresqlTransactionManager"
)
@Profile("test")
@AllArgsConstructor
public class PostgreSQLDataSourceConfig extends  DataSourceConfig {

    final PostgreSQLDataSourceProperties postgreSQLDataSourceProperties;

    @Bean(name = "postgresqlDataSource")
    public DataSource postgresqlDataSource() {
        // Configure PostgreSQL datasource properties
        return getDataSource(postgreSQLDataSourceProperties);
    }

    @Bean(name = "postgresqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(
            @Qualifier("postgresqlDataSource") DataSource dataSource,
            JpaProperties jpaProperties) {
        // Configure EntityManagerFactory for MySQL
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.amichdev.spring.dataaccess.course"); // Replace with your package name
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaPropertyMap(jpaProperties.getProperties());

        return em;
    }

    @Bean(name = "postgresqlTransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory postgresqlEntityManagerFactory) {
        // Configure TransactionManager for MySQL
        return new JpaTransactionManager(postgresqlEntityManagerFactory);
    }
}
