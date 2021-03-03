package com.lambdaschool.watermyplants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@PropertySource(value="file:/Users/yf/waterplant.properties", ignoreResourceNotFound = true)
public class WatermyplantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatermyplantsApplication.class, args);
    }

}
