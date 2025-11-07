package by.savik.Jobbler.service;

import by.savik.Jobbler.dto.AreaDto;
import by.savik.Jobbler.dto.VacancyDto;

import java.util.List;

public interface RetroClientServiceInterface {

    List<VacancyDto> getVacancyListFromHeadHunter(String text);

    List<AreaDto> getAllAreasList();

    List<AreaDto> getAreaByHeadHunterIdAndSetCountry(Long id, String countryName);
}
