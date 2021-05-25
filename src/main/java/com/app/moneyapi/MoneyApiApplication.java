package com.app.moneyapi;

import com.app.moneyapi.config.property.MoneyApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MoneyApiProperty.class})
public class MoneyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyApiApplication.class, args);
    }

}
