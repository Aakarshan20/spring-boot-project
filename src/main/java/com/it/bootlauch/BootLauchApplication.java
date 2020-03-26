package com.it.bootlauch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BootLauchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootLauchApplication.class, args);
    }

}
