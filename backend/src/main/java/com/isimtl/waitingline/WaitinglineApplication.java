package com.isimtl.waitingline;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.*;


@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories("com.isimtl.waitingline.repository")
@EntityScan(basePackages = {"com.isimtl.waitingline.entity"})

public class WaitinglineApplication {

    private static Class applicationClass = WaitinglineApplication.class;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(WaitinglineApplication.class, args);
    }

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(basePackage("com.isimtl.waitingline.controller")).build();
    }
}
