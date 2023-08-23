package com.playground.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EntityScan(basePackages = "com.playground.core.domain")
@EnableJpaAuditing
public class JpaConfig {
}
