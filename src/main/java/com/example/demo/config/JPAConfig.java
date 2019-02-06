package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The Class JPAConfig.
 */
@Configuration
@EnableJpaRepositories("com.example.demo.person")
public class JPAConfig {

}
