package io.creatine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulith;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@Modulith(systemName = "Creatine Platform",
        additionalPackages = "io.creatine",
        useFullyQualifiedModuleNames = true,
        sharedModules = {
                "io.creatine.support",
                "io.creatine.sharedkernel"
        })
public class CreatineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreatineApplication.class, args);
    }

}
