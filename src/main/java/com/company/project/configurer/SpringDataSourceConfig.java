package com.company.project.configurer;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class SpringDataSourceConfig {
    @Bean(name = "userDataSource")
    @Primary
    @Qualifier("userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSource userDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "newjpressDataSource")
    @Qualifier("newjpressDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.newjpress")
    public DataSource newjpressDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "userJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("userDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "newjpressJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("newjpressDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}