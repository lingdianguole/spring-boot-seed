package com.company.project.configurer;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Maps;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DataSourceConfigurer {
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSource userDataSource() {
        return DataSourceBuilder
                .create()
                .type(DruidDataSource.class)
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.press")
    public DataSource pressDataSource() {
        return DataSourceBuilder
                .create()
                .type(DruidDataSource.class)
                .build();
    }

    @Bean
    public DynamicDataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = Maps.newHashMap();
        targetDataSources.put("user", userDataSource());
        targetDataSources.put("press", pressDataSource());
        dynamicDataSource.setTargetDataSources(targetDataSources);
        DataSourceContextHolder.supportList.addAll(targetDataSources.keySet());
        return dynamicDataSource;
    }

}
