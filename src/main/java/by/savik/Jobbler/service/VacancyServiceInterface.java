package by.savik.Jobbler.service;

import by.savik.Jobbler.entity.Vacancy;

public interface VacancyServiceInterface {

    Vacancy createVacancy(Vacancy vacancy);

    boolean isPresentByHeadHunterId(Vacancy vacancy);
}
