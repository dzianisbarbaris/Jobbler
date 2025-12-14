package by.savik.jobbler.config;

import by.savik.jobbler.entity.ApiHeadHunterClientInterface;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitConfig {
    @Value("${api.hh.url}")
    private String url;

    private Retrofit getRetrofitInstance() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
    }

    @Bean
    public ApiHeadHunterClientInterface getApiHeadHunter() {
        return getRetrofitInstance().create(ApiHeadHunterClientInterface.class);
    }
}
