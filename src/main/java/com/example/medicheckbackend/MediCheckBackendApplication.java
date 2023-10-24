package com.example.medicheckbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MediCheckBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediCheckBackendApplication.class, args);
    }

}
