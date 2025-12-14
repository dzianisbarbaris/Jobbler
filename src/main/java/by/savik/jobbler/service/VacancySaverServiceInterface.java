package by.savik.jobbler.service;

import by.savik.jobbler.entity.Vacancy;

import java.util.List;

public interface VacancySaverServiceInterface {

    List<Vacancy> convertDtoToVacancyAndSave(String keyWord);

    List<Vacancy> convertDailyDtoToVacanciesAndSave();
}
