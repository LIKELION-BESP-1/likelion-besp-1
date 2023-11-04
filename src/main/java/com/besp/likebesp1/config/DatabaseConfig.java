package com.besp.likebesp1.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Autowired
    ApplicationContext applicationContext;

    @Bean //객체 생성
    public SqlSessionFactory makeSqlSessionFactory(DataSource dataSource) throws Exception {
        System.out.println("************************");

        final SqlSessionFactoryBean factory
                = new SqlSessionFactoryBean();

        factory.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver
                = new PathMatchingResourcePatternResolver();

        Resource configLocation =
                resolver.getResource("classpath:mybatis-config.xml");

        factory.setConfigLocation(configLocation);

        return factory.getObject();
    }

    @Bean
    public SqlSessionTemplate makeSqlSession(
            SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }
}
