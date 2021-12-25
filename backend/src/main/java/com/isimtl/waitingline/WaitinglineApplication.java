package com.isimtl.waitingline;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;


@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories("com.isimtl.waitingline.repository")
@EntityScan(basePackages = {"com.isimtl.waitingline.entity"})

public class WaitinglineApplication {
    public static void main(String[] args) throws Exception {
//        Resource resource = new ClassPathResource("serviceAccountKey.json");
//        InputStream inputStream = resource.getInputStream();
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(inputStream))
//                .build();
//
//        FirebaseApp.initializeApp(options);


        SpringApplication.run(WaitinglineApplication.class, args);
    }

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.isimtl.waitingline.controller")).build();
    }

}
