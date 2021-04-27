package com.galdovich.esm.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.galdovich.esm")
@EntityScan(basePackages = "com.galdovich.esm")
public class PersistenceConfig {
}
