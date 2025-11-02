package by.savik.Jobbler.harvester.service;

import by.savik.Jobbler.harvester.entity.AreaDto;
import by.savik.Jobbler.harvester.entity.ItemDto;
import by.savik.Jobbler.harvester.entity.RetroClient;
import by.savik.Jobbler.harvester.entity.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RetroClientService implements RetroClientServiceInterface {
    private final RetroClient retroClient;

    @Autowired
    public RetroClientService(RetroClient retroClient) {
        this.retroClient = retroClient;
    }


    public List<ItemDto> getVacancyListFromHeadHunter(String text) {
        List<ItemDto> itemsList = new ArrayList<>();
        try {
            Response<Root> firstRootResponse = retroClient.getApiHeadHunter().getFirstJson(text).execute();
            System.out.println("Request done!");
            itemsList.addAll(firstRootResponse.body().getItems());
            int pages = firstRootResponse.body().getPages();
            for (int i = 2; i < pages; i++) {
                Response<Root> nextRootResponse = retroClient.getApiHeadHunter().getAllOtherJsons(i, text).execute();
                itemsList.addAll(nextRootResponse.body().getItems());
                Thread.sleep(200);
            }
            itemsList.forEach(System.out::println);
            System.out.println("По запросу найдено " + itemsList.size() + " вакансий");
        } catch (IOException | NullPointerException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        return itemsList;
    }

    public List<AreaDto> getAllAreasList(){
        try {
            Response<List<AreaDto>> response = retroClient.getApiHeadHunter().getAllAreas().execute();
            System.out.println("Request done!");
            List<AreaDto> areas = response.body();
            areas.forEach(System.out::println);
            return areas;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
