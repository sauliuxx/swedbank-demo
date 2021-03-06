package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The Class JPAConfig.
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.example.demo.person", "com.example.demo.group", "com.example.demo.task" })
public class JPAConfig {

}
