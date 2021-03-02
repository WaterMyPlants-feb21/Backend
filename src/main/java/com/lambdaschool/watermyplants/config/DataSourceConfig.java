package com.lambdaschool.watermyplants.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Value("${local.run.db:h2}")
    private String dbValue;

    @Value("${spring.datasource.url:}")
    private String datasourceURL;

    @Bean
    public DataSource dataSource(){
        if(dbValue.equalsIgnoreCase("postgresql")){
            //configure for postgres
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("org.postgresql.Driver");
            config.setUsername("postgres");
            config.setPassword("password");
            config.setJdbcUrl(datasourceURL);
            return new HikariDataSource(config);

        } else{
            //configure for h2 default
            String myURLString = "jdbc:h2:mem:testdb";
            String myDriverClass = "org.h2.Driver";
            String myDbUser = "sa";
            String myDbPassword = "";

            return DataSourceBuilder.create()
                    .username(myDbUser)
                    .password(myDbPassword)
                    .url(myURLString)
                    .driverClassName(myDriverClass)
                    .build();
        }
    }
}

