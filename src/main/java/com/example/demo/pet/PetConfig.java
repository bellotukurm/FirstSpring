package com.example.demo.pet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PetConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            PetRepository repository) {
        return args -> {
            Pet tom = new Pet(
                    "Tom",
                    "tomcat@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );
            Pet jerry = new Pet(
                    "Jerry",
                    "jerrymouse@gmail.com",
                    LocalDate.of(2016, Month.JANUARY, 5)
            );

            repository.saveAll(
                    List.of(tom, jerry)
            );
        };
    }
}
