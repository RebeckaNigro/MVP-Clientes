package com.example.listadeclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    	info = @Info(
        title = "API CLIENTE",
        version = "1.0",
        description = "API PARA GERENCIAR CLIENTES PESSOA FÍSICA"
    	)
)
@SpringBootApplication
public class ListadeclientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListadeclientesApplication.class, args);
	}

}
