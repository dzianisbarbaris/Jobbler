package by.savik.Jobbler.service;

import by.savik.Jobbler.entity.ApiHeadHunterClientInterface;
import by.savik.Jobbler.dto.AreaDto;
import by.savik.Jobbler.dto.VacancyDto;
import by.savik.Jobbler.dto.RootDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RetroClientService implements RetroClientServiceInterface {
    private final ApiHeadHunterClientInterface apiClient;

    @Autowired
    public RetroClientService(ApiHeadHunterClientInterface apiClient) {
        this.apiClient = apiClient;
    }


    public List<VacancyDto> getVacancyListFromHeadHunter(String text) {
        List<VacancyDto> finalItemsList = new ArrayList<>();
        try {
            Response<RootDto> firstRootResponse = apiClient.getFirstJson(text).execute();
            if (!firstRootResponse.isSuccessful()) {
                throw new HttpException(firstRootResponse);
            }
            List<VacancyDto> items = firstRootResponse.body().getItems();
            if (items.isEmpty()) {
                throw new IllegalStateException("Items list is empty!");
            }
            finalItemsList.addAll(items);
            int pages = firstRootResponse.body().getPages();
            for (int i = 1; i < pages; i++) {
                Response<RootDto> nextRootResponse = apiClient.getAllOtherJsons(i, text).execute();
                if (!nextRootResponse.isSuccessful()) {
                    throw new HttpException(nextRootResponse);
                }
                List<VacancyDto> itemsNext = nextRootResponse.body().getItems();
                if (itemsNext.isEmpty()) {
                    throw new IllegalStateException("Items list is empty!");
                }
                finalItemsList.addAll(itemsNext);
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        return finalItemsList;
    }

    public List<AreaDto> getAllAreasList() {
        try {
            Response<List<AreaDto>> response = apiClient.getAllAreas().execute();
            if (!response.isSuccessful()) {
                throw new HttpException(response);
            }
            List<AreaDto> areas = response.body().stream().flatMap(areaDto -> areaDto.getAreas().stream()).toList();
            if (areas.isEmpty()) {
                throw new IllegalStateException("Areas list is empty!");
            }
            return areas;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<AreaDto> getAreaByHeadHunterIdAndSetCountry(Long id, String countryName) {
        try {
            Response<AreaDto> response = apiClient.getAreaByHeadHunterId(id).execute();
            if (!response.isSuccessful()) {
                throw new HttpException(response);
            }
            List<AreaDto> areas = response.body().getAreas();
            if (areas.isEmpty()) {
                throw new IllegalStateException("Areas list is empty!");
            }
            return areas.stream().flatMap(areaDto -> areaDto.getAreas().stream()
                    .peek(areaDto1 -> areaDto1.setCountry(countryName))).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
