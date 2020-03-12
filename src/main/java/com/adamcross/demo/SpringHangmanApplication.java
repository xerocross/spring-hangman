package com.adamcross.demo;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

@SpringBootApplication
public class SpringHangmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHangmanApplication.class, args);
	}

	@Bean
	public SecretPhrases secretPhrases() throws IOException {
		try (InputStream programData = new ClassPathResource("SecretPhrases.yaml").getInputStream()) {
			return new Yaml().loadAs(programData, SecretPhrases.class);
		}
	}
}
