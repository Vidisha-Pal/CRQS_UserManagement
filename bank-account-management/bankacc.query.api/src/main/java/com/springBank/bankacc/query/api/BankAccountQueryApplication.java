package com.springBank.bankacc.query.api;

import com.springBank.bankacc.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
@EntityScan(basePackages = "com.springBank.bankacc.core.models")
public class BankAccountQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountQueryApplication.class, args);
    }

}
