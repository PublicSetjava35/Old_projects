package org.example.ClientMainApp;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ClientMainAppApplication {
	public static void main(String[] args) {
//		SpringApplication.run(ClientMainAppApplication.class, args);
		List<String> names = Arrays.asList("Ivan", "Anna", "Igor", "Boris");
		names.stream().filter(name -> name.startsWith("I"))
				.map(String::toUpperCase).forEach(System.out::println);
		BigDecimal bigDecimal = BigDecimal.valueOf(1.);
	}
}
