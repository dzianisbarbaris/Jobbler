package by.savik.jobbler.config;

import by.savik.jobbler.entity.ApiHeadHunterClientInterface;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Value("${api.hh.url}")
    private String url;

    private Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Bean
    public ApiHeadHunterClientInterface getApiHeadHunter() {
        return getRetrofitInstance().create(ApiHeadHunterClientInterface.class);
    }


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
                                .description("Development server"),
                        new Server()
                                .url("https://api.vacancies.com")
                                .description("Production server")
                ));
    }
}
