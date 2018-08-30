package com.example.learn_springboot_more;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LearnSpringbootMoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringbootMoreApplication.class, args);
    }
}
