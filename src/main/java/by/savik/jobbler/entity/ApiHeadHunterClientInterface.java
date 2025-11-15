package by.savik.jobbler.entity;

import by.savik.jobbler.dto.AreaDto;
import by.savik.jobbler.dto.RootDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;


public interface ApiHeadHunterClientInterface {

    @GET("vacancies?per_page=100&page=0&period=1&")
    Call<RootDto> getFirstJson(@Query("text") String text);

    @GET("vacancies?per_page=100&period=1&")
    Call<RootDto> getAllOtherJsons(@Query("page") int page, @Query("text") String text);

    @GET("areas")
    Call<List<AreaDto>> getAllAreas();

    @GET("areas/{id}")
    Call<AreaDto> getAreaByHeadHunterId(@Path("id") Long id);
}
