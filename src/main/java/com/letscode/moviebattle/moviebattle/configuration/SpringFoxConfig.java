package com.letscode.moviebattle.moviebattle.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SpringFoxConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		/*
		 * ApiInfo apiInfo = new ApiInfo("MovieBattle",
		 * "Aplicação do desafio da let´s code - Movies Battle", "Moviebattle v1",
		 * "Terms of service", "antonio.ezequiel@ufpe.br", "License of API",
		 * "https://swagger.io/docs/");
		 */
		return new ApiInfo("MovieBattle", "<b>Aplicação do desafio da let´s code - Movies Battle.</b>"
				+ "<br/> A primeira ação que deve ser realizada para utilizar o sistema é executar o endpoint<b> /carregar uma única vez</b>. Esse endpoint carrega a banco com os usuários padrão"
				+ " <b>(login: antonio, senha: 123456 - login: jose, senha: 123456)</b>. <br/> Após essa ação, é necessário realizar o <b> login (/auth) </b> no sistema para a geração do token. "
				+ "O token deve ser passado para os <b>endpoint restritos(/iniciar,  /sortear, /jogar, /finalizar)</b>. <br/> O jogo inicia com o endpoint <b>/iniciar</b>, e continua com os demais endpoits. "
				+ "Sempre deve usar o endpoint <b>/sortear e /jogar até decidir /finalizar o jogo ou perder as vidas.</b> <br/>"
				+ "O jogo pode ser finalizado a qualquer momento <b>/finalizar </b> e é possível consultar o /ranking a qualquer momento.", "API MOVIE BATTLE",
				"Terms of service", new Contact("Antonio Ezequiel", "www.example.com", "antonio.ezequiel@ufpe.br"), "License of API", "API license URL", Collections.EMPTY_LIST);
		// return apiInfo;
	}
}
