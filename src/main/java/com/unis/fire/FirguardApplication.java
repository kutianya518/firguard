package com.unis.fire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.unis.fire.mapper")
@EnableScheduling
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FirguardApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirguardApplication.class, args);
    }

}
