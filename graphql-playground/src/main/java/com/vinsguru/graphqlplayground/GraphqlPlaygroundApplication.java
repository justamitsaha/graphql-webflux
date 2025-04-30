package com.vinsguru.graphqlplayground;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = "com.vinsguru.graphqlplayground.${lec}")
//, exclude = R2dbcAutoConfiguration.class
@EnableR2dbcRepositories(basePackages = "com.vinsguru.graphqlplayground.${lec}")
public class GraphqlPlaygroundApplication {

	private static final Log log = LogFactory.getLog(GraphqlPlaygroundApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GraphqlPlaygroundApplication.class, args);
		log.info("Custom Startup url --> http://localhost:8080/graphiql.html");
		log.info("Startup url --> http://localhost:8080/graphiql");
	}
}
