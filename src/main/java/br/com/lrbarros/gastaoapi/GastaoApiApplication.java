package br.com.lrbarros.gastaoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.lrbarros.gastaoapi.config.property.GastaoApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(GastaoApiProperty.class) //para poder usar as configuracoes externamete
public class GastaoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GastaoApiApplication.class, args);
	}

}
