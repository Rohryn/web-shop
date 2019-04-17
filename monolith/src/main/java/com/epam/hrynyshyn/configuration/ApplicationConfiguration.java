package com.epam.hrynyshyn.configuration;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author by Roman Hrynyshyn
 * Created on 2019-04-16.
 */
@Configuration
public class ApplicationConfiguration {
    // TODO: 2019-04-16 provide datasource  
    @Bean
    public DataSource dataSource() {
        return new MysqlDataSource();
    }
}
