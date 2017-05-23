package org.xi.quick.usermanager.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"org.xi.quick.usermanager.mapper"})
public class MyBatisConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public ResourcePatternResolver resourcePatternResolver() {
        return new PathMatchingResourcePatternResolver();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(ResourcePatternResolver resourcePatternResolver) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:mapper/*.xml"));
        sessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return sessionFactoryBean.getObject();
    }
}
