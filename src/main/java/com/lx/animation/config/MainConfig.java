package com.lx.animation.config;

import com.lx.animation.Aspect.AspectService;
import com.lx.animation.util.AddressUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PreferencesPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


//@PropertySource("classpath:/jdbc.properties")
//@ComponentScan(value = "com.lx.animation")
//@EnableAspectJAutoProxy
//@EnableTransactionManagement
//@Configuration
public class MainConfig {

    Logger LOGGER = LogManager.getLogger(MainConfig.class);

    @Bean
    public AddressUtil addressUtil() {
        return new AddressUtil();
    }

    @Bean
    public MyFactoryBean myFactoryBean() {
        return new MyFactoryBean();
    }

    @Bean
    public MyProperties myProperties() {
        return new MyProperties();
    }

    @Bean
    @Lazy
    public BeanClass beanClass() {
        return new BeanClass();
    }

    @Bean
    public AspectService aspectService() {
        return new AspectService();
    }

    @Bean
    public UserTestService userTestService() {
        return new UserTestService();
    }

    @Bean
    public List<String> list() {
       return new ArrayList<String>();
    }

    @Bean
    public DataSource dataSource(@Value("${jdbc.driver}") String drivers, @Value("${jdbc.url}") String url, @Value("${jdbc.username}") String username, @Value("${jdbc.password}") String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(drivers);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    //配置sql工厂
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    //扫描mapper
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.lx.animation");
        return configurer;
    }

    //配置开启DataSourceTransactionManager
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }


}
