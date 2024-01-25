package com.amichdev.spring.dataaccess.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.amichdev.spring.dataaccess.user",
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager"
)
@Profile("test")
@AllArgsConstructor
public class MysqlDataSourceConfig extends DataSourceConfig {

    final DataSourceProperties mysqlDataSourceProperties;
    @Primary
    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource() {
        // Configure MySQL datasource properties
       return getDataSource(mysqlDataSourceProperties);
    }

    @Primary
    @Bean(name = "mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("mysqlDataSource") DataSource dataSource,
            JpaProperties jpaProperties) {
        // Configure EntityManagerFactory for MySQL
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.amichdev.spring.dataaccess.user");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaPropertyMap(jpaProperties.getProperties());

        return em;
    }

    @Primary
    @Bean(name = "mysqlTransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory mysqlEntityManagerFactory) {
        // Configure TransactionManager for MySQL
        return new JpaTransactionManager(mysqlEntityManagerFactory);
    }


}
