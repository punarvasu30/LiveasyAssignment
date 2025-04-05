package com.example.liveasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class LiveasyApplication {

    private static final Logger logger = LoggerFactory.getLogger(LiveasyApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Liveasy Application");
        SpringApplication.run(LiveasyApplication.class, args);
        logger.info("Liveasy Application started successfully");
    }
}