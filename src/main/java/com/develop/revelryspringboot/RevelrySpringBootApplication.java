package com.develop.revelryspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.develop.revelryspringboot.configuration.properties.MinioProperties;

@SpringBootApplication
@EnableConfigurationProperties({SecurityProperties.class, MinioProperties.class, CacheProperties.class})
public class RevelrySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RevelrySpringBootApplication.class, args);
    }
}
