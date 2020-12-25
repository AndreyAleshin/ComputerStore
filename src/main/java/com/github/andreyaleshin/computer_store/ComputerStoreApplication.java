package com.github.andreyaleshin.computer_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://localhost:8080/
@SpringBootApplication
public class ComputerStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputerStoreApplication.class, args);
    }

}

// todo write DOCS
// todo in pom.xml <version></version> разобраться
// todo should add README.md (description) when committing project to GitHub
// todo try using Docker to containerize PostgreSQL
// todo add favicon