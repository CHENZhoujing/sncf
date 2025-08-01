package com.a.sncf.member.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan(basePackages = {"com.a"})
@MapperScan("com.a.sncf.member.mapper")
public class MemberApplication {

    private static final Logger LOG = LoggerFactory.getLogger(MemberApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MemberApplication.class);
        Environment env = application.run(args).getEnvironment();
        LOG.info("Application '{}' is running on port: {}{}", env.getProperty("spring.application.name"), env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
    }
}