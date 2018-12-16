package com.kevin.springboot.learning.databasechapter5.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceShow implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(DataSourceShow.class);
    private ApplicationContext applicationContext = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        logger.info("-------------------------------------------------------------------------------------");
        logger.info("dataSource bean Type: " + dataSource.getClass().getName());
        logger.info("-------------------------------------------------------------------------------------");
    }
}
