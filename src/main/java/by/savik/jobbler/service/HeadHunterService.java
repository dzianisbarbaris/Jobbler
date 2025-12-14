package by.savik.jobbler.service;

import by.savik.jobbler.entity.ApiHeadHunterClientInterface;
import by.savik.jobbler.dto.AreaDto;
import by.savik.jobbler.dto.VacancyDto;
import by.savik.jobbler.dto.RootDto;
import by.savik.jobbler.exception.HeadHunterApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@Slf4j
public class HeadHunterService implements HeadHunterServiceInterface {
    private final ApiHeadHunterClientInterface apiClient;
    private final Random random = new Random();

    @Autowired
    public HeadHunterService(ApiHeadHunterClientInterface apiClient) {
        this.apiClient = apiClient;
    }

    public List<VacancyDto> getVacancyListFromHeadHunter(String text) {
        List<VacancyDto> finalItemsList = new ArrayList<>();
        try {
            Response<RootDto> firstRootResponse = apiClient.getFirstJson(text).execute();
            if (!firstRootResponse.isSuccessful()) {
                throw new HttpException(firstRootResponse);
            }
            List<VacancyDto> items = Objects.requireNonNull(firstRootResponse.body()).getItems();
            if (items.isEmpty()) {
                throw new IllegalStateException("Items list is empty!");
            }
            finalItemsList.addAll(items);
            int pages = Objects.requireNonNull(firstRootResponse.body()).getPages();
            int maxPages = Math.min(pages, 20);
            for (int i = 1; i < maxPages; i++) {
                Response<RootDto> nextRootResponse = apiClient.getAllOtherJsons(i, text).execute();
                if (!nextRootResponse.isSuccessful()) {
                    throw new HttpException(nextRootResponse);
                }
                List<VacancyDto> itemsNext = Objects.requireNonNull(nextRootResponse.body()).getItems();
                if (itemsNext.isEmpty()) {
                    throw new IllegalStateException("Items list is empty!");
                }
                finalItemsList.addAll(itemsNext);
                    Thread.sleep(random.nextInt(2000, 5000));
                }
        } catch (InterruptedException | IOException e) {
            throw new HeadHunterApiException("Ошибка при обработке запроса к HeadHunter", e);
        }
        log.debug(String.valueOf(finalItemsList.size()));
        return finalItemsList;
    }

    public List<AreaDto> getAllAreasList() {
        try {
            Response<List<AreaDto>> response = apiClient.getAllAreas().execute();
            if (!response.isSuccessful()) {
                throw new HttpException(response);
            }
            List<AreaDto> areas = Objects.requireNonNull(response.body()).stream()
                    .flatMap(areaDto -> areaDto.getAreas().stream()).toList();
            if (areas.isEmpty()) {
                throw new IllegalStateException("Areas list is empty!");
            }
            log.debug(String.valueOf(areas.size()));
            return areas;
        } catch (IOException e) {
            throw new HeadHunterApiException("Ошибка при обработке запроса к HeadHunter", e);
        }
    }

    public List<AreaDto> getAreaByHeadHunterIdAndSetCountry(Long id, String countryName) {
        try {
            Response<AreaDto> response = apiClient.getAreaByHeadHunterId(id).execute();
            if (!response.isSuccessful()) {
                throw new HttpException(response);
            }
            List<AreaDto> areas = Objects.requireNonNull(response.body()).getAreas();
            if (areas.isEmpty()) {
                throw new IllegalStateException("Areas list is empty!");
            }
            for (AreaDto areaDto : areas){
                areaDto.setCountry(countryName);
            }
            List<AreaDto> listAreas = areas.stream().flatMap(areaDto -> areaDto.getAreas().stream()
                    .peek(areaDto1 -> areaDto1.setCountry(countryName))).toList();
            areas.addAll(listAreas);
            log.debug(String.valueOf(areas.size()));
            return areas;
        } catch (IOException e) {
            throw new HeadHunterApiException(e.getMessage());
        }
    }
}
