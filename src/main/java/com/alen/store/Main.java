package com.alen.store;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 *  Specify some database connection information
 *  in the Spring Boot application configuration file (application.properties)
 *  @EnableJpaEditing: tracking and logging user activity across the application.
 */
@SpringBootApplication
@EnableJpaAuditing
public class Main implements CommandLineRunner{

    public static void main(String[] args)  {
        SpringApplication.run(Main.class, args);

        /* Connect to PostgresDB Manually Without Spring Configuration. */
        //DBConnector db = new DBConnector();
        //db.connect();
    }


    /**
     CommandLineRunner is an interface used to indicate that a bean
     should run when it is contained within a SpringApplication.
     */
    @Override
    public void run(String... args) throws Exception {
        //TODO: ??
    }
}


