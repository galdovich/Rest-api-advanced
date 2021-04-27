package com.galdovich.esm.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.galdovich.esm")
@EnableTransactionManagement
public class ServiceConfiguration {
}
