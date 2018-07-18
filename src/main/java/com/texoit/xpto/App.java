package com.texoit.xpto;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.texoit.xpto.controller.csv.CityControllerCsv;

/**
 * Classe que inicia a aplicação
 * @author Cleiton
 */
@SpringBootApplication
public class App {
	
	@Autowired
	CityControllerCsv cityControllerCsv;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	/**
	 * 1 - Leitura do CSV para a base de dados<br>
	 */
	@PostConstruct
    public void init() {
		cityControllerCsv.csvToDataBase();
    }
	
}
