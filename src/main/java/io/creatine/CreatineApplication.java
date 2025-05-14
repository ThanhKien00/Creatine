package io.creatine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulithic;

@SpringBootApplication
@Modulithic(systemName = "Creatine Platform",
        additionalPackages = "io.creatine",
        useFullyQualifiedModuleNames = true)
public class CreatineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreatineApplication.class, args);

    }

}
