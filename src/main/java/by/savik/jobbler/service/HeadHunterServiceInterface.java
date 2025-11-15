package by.savik.jobbler.service;

import by.savik.jobbler.dto.AreaDto;
import by.savik.jobbler.dto.VacancyDto;

import java.util.List;

public interface HeadHunterServiceInterface {

    List<VacancyDto> getVacancyListFromHeadHunter(String text);

    List<AreaDto> getAllAreasList();

    List<AreaDto> getAreaByHeadHunterIdAndSetCountry(Long id, String countryName);
}
