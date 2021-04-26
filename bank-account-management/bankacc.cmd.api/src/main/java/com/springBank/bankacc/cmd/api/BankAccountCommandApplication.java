package com.springBank.bankacc.cmd.api;

import com.springBank.bankacc.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class BankAccountCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountCommandApplication.class, args);
    }

}
