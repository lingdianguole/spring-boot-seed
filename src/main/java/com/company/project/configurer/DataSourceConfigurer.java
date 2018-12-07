package com.company.project.configurer;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.google.common.collect.Maps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement(order = 2)//由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
public class DataSourceConfigurer {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.user")
    public DataSource userDataSource() {
        return DruidDataSourceBuilder
                .create()
                .build();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.press")
    public DataSource pressDataSource() {
        return DruidDataSourceBuilder
                .create()
                .build();
    }

    @Bean
    public DynamicDataSource dataSource() {
        Map<Object, Object> targetDataSources = Maps.newHashMap();
        targetDataSources.put("user", userDataSource());
        targetDataSources.put("press", pressDataSource());
        DataSourceContextHolder.supportList.addAll(targetDataSources.keySet());
        return new DynamicDataSource(userDataSource(),targetDataSources);
    }

}
