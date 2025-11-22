package by.savik.jobbler.service;

import by.savik.jobbler.entity.Vacancy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface VacancyServiceInterface {

    Vacancy createVacancy(Vacancy vacancy);

    boolean isAbsentByHeadHunterId(Vacancy vacancy);

    List<Vacancy> getAllVacancies();

    List<Vacancy> getVacanciesByName(String keyWord);

    List<Vacancy> getVacanciesByAddressCity(String addressCity);

    List<Vacancy> getVacanciesByAreaCountryName(String countryName);

    void deleteAllVacancies();

    void deleteByCreatedDateBefore(LocalDateTime date);
}
