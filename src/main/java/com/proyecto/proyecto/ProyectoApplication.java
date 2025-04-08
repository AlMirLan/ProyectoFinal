package com.proyecto.proyecto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.proyecto.proyecto.repository.BoardGameRepositoryI;
import com.proyecto.proyecto.storage.StorageService;

/**
 * Clase principal
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
@SpringBootApplication
@ComponentScan
public class ProyectoApplication extends SpringBootServletInitializer {

	/**
	 * Método principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProyectoApplication.class);
	}

	@Bean
	CommandLineRunner init(StorageService storageService, BoardGameRepositoryI boardGameRepository) {
		return (args) -> {
			storageService.init();
		};

	}
}
