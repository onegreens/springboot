package com.example.learn_springboot_more;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LearnSpringbootMoreApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LearnSpringbootMoreApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LearnSpringbootMoreApplication.class, args);
    }

}
