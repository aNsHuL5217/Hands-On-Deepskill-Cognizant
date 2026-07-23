package com.example.EmployeeManagementSystem.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "reportingDataSource")
    @ConfigurationProperties(prefix = "app.reporting.datasource")
    public DataSource reportingDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "reportingJdbcTemplate")
    public JdbcTemplate reportingJdbcTemplate(
            @Qualifier("reportingDataSource") DataSource reportingDataSource
    ) {
        return new JdbcTemplate(reportingDataSource);
    }
}
