package by.savik.Jobbler.harvester.service;

import by.savik.Jobbler.database.entity.Item;
import by.savik.Jobbler.harvester.entity.AreaDto;
import by.savik.Jobbler.harvester.entity.ItemDto;

import java.io.IOException;
import java.util.List;

public interface RetroClientServiceInterface {

    List<ItemDto> getVacancyListFromHeadHunter(String text);

    List<AreaDto> getAllAreasList();
}
