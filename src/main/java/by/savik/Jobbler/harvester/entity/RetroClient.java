package by.savik.Jobbler.harvester.entity;

import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class RetroClient {

    private static final String ROOT_URL  = "https://api.hh.ru/";

    public RetroClient() {
    }

    public Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiHeadHunterClientInterface getApiHeadHunter(){
        return getRetrofitInstance().create(ApiHeadHunterClientInterface.class);
    }
}
