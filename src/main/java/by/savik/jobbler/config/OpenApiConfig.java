package by.savik.jobbler.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Job Management API")
                        .description("RESTful API for managing vacancies")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Vacancy Management Team")
                                .email("support@jobbler.com")
                                .url("https://hh-jobbler.com")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8081")
                                .description("Development server")
                ));
    }
}
