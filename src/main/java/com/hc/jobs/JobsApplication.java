package com.hc.jobs;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobsApplication {
    
    private static Logger ROOT_LOGGER = Logger.getLogger(JobsApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(JobsApplication.class, args);
        ROOT_LOGGER.log(Level.INFO, "[X_ X] Iniciando la aplicación [X _ X]");        
        
    }

}
