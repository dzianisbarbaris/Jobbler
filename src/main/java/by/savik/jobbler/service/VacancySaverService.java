package by.savik.jobbler.service;

import by.savik.jobbler.dto.VacancyDto;
import by.savik.jobbler.entity.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacancySaverService implements VacancySaverServiceInterface {

    private final List<String> dailyKeyWords = List.of("Разработчик", "Тестировщик", "Java", "JavaScript",
            "DevOps", "IT Manager", "Developer", "Python", "1C", "Quality Assurance", "Business Analyst", "Аналитик");

    private final AreaServiceInterface areaService;
    private final EmployerServiceInterface employerService;
    private final VacancyServiceInterface vacancyService;
    private final DtoConverterServiceInterface dtoConverterService;
    private final HeadHunterServiceInterface headHunterService;

    @Autowired
    public VacancySaverService(AreaServiceInterface areaService, EmployerServiceInterface employerService,
                               VacancyServiceInterface vacancyService, DtoConverterServiceInterface dtoConverterService,
                               HeadHunterServiceInterface headHunterService) {
        this.areaService = areaService;
        this.employerService = employerService;
        this.vacancyService = vacancyService;
        this.dtoConverterService = dtoConverterService;
        this.headHunterService = headHunterService;
    }

    @Transactional
    public List<Vacancy> convertDtoToVacancyAndSave(String keyWord) {
        List<Vacancy> finalVacancyList = new ArrayList<>();
        List<VacancyDto> vacancyDtoList = headHunterService.getVacancyListFromHeadHunter(keyWord);
        List<Vacancy> vacancyList = dtoConverterService.convertAllDtoToVacancy(vacancyDtoList);
        for (Vacancy vacancy : vacancyList) {
            if (vacancyService.isAbsentByHeadHunterId(vacancy)) {
                if (areaService.isAbsentByHeadHunterId(vacancy.getArea())) {
                    areaService.createArea(vacancy.getArea());
                }
                if (employerService.isAbsentByHeadHunterId(vacancy.getEmployer())) {
                    employerService.createEmployer(vacancy.getEmployer());
                }
                vacancyService.createVacancy(vacancy);
                finalVacancyList.add(vacancy);
            }
        }
        return finalVacancyList;
    }

    @Transactional
    public List<Vacancy> convertDailyDtoToVacanciesAndSave() {
        List<Vacancy> finalVacancyList = new ArrayList<>();
        for (String keyWord : dailyKeyWords) {
            List<VacancyDto> vacancyDtoList = headHunterService.getVacancyListFromHeadHunter(keyWord);
            List<Vacancy> vacancyList = dtoConverterService.convertAllDtoToVacancy(vacancyDtoList);
            for (Vacancy vacancy : vacancyList) {
                if (vacancyService.isAbsentByHeadHunterId(vacancy)) {
                    if (areaService.isAbsentByHeadHunterId(vacancy.getArea())) {
                        areaService.createArea(vacancy.getArea());
                    }
                    if (employerService.isAbsentByHeadHunterId(vacancy.getEmployer())) {
                        employerService.createEmployer(vacancy.getEmployer());
                    }
                    vacancyService.createVacancy(vacancy);
                    finalVacancyList.add(vacancy);
                }
            }
        }
        return finalVacancyList;
    }
}
