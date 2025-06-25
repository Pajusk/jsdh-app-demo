package cz.jsdh.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hlavní třída Spring Boot aplikace.
 * Spouští embedded server a inicializuje celý Spring kontext.
 */
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		// Spuštění aplikace
		SpringApplication.run(BackendApplication.class, args);
	}

}
