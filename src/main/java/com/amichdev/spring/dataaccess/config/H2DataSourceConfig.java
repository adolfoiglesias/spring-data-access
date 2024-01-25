package com.amichdev.spring.dataaccess.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.amichdev.spring.dataaccess.repository.defaults"
)
@Profile("local")
@AllArgsConstructor
public class H2DataSourceConfig extends DataSourceConfig {

    final private DataSourceProperties dataSourceProperties;

    @Bean
    public DataSource h2DataSource() {
        return getDataSource(dataSourceProperties);
    }

}
