package io.creatine;

import org.springframework.boot.SpringApplication;

public class TestCreatineApplication {

	public static void main(String[] args) {
		SpringApplication.from(CreatineApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
