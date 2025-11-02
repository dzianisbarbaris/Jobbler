package by.savik.Jobbler.harvester.entity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;


public interface ApiHeadHunterClientInterface {

    @GET("vacancies?per_page=100&page=1&period=1&")
    Call<Root> getFirstJson(@Query("text") String text);

    @GET("vacancies?per_page=100&period=1&")
    Call<Root> getAllOtherJsons(@Query("page") int page, @Query("text") String text);

    @GET("areas")
    Call<List<AreaDto>> getAllAreas();
}
