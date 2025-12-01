package by.savik.jobbler.service;

import by.savik.jobbler.entity.Area;
import by.savik.jobbler.entity.Employer;
import by.savik.jobbler.entity.Vacancy;
import by.savik.jobbler.exception.ResourceNotFoundException;
import by.savik.jobbler.exception.VacancyNotFoundException;
import by.savik.jobbler.repository.AreaRepository;
import by.savik.jobbler.repository.EmployerRepository;
import by.savik.jobbler.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VacancyService implements VacancyServiceInterface {

    private final VacancyRepository vacancyRepository;
    private final AreaRepository areaRepository;
    private final EmployerRepository employerRepository;

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository, AreaRepository areaRepository, EmployerRepository employerRepository) {
        this.vacancyRepository = vacancyRepository;
        this.areaRepository = areaRepository;
        this.employerRepository = employerRepository;
    }

    @Transactional(readOnly = true)
    public List<Vacancy> getAllVacancies() {
        return vacancyRepository.findAllWithEmployerAndArea();
    }

    @Transactional(readOnly = true)
    public List<Vacancy> getVacanciesByName(String name) {
        List<Vacancy> vacancyList = vacancyRepository.findByNameContainingIgnoreCase(name);
        if (vacancyList.isEmpty()) {
            throw new VacancyNotFoundException("Vacancy not found by keyWord: " + name);
        } else {
            return vacancyList;
        }
    }

    @Transactional(readOnly = true)
    public List<Vacancy> getVacanciesByAddressCity(String addressCity) {
        List<Vacancy> vacancyList = vacancyRepository.findByAddressCityContainingIgnoreCase(addressCity);
        if (vacancyList.isEmpty()) {
            throw new VacancyNotFoundException("Vacancy not found by addressCity: " + addressCity);
        } else {
            return vacancyList;
        }
    }

    @Transactional(readOnly = true)
    public List<Vacancy> getVacanciesByAreaCountryName(String countryName) {
        List<Vacancy> vacancyList = vacancyRepository.findByAreaCountryNameIgnoreCase(countryName);
        if (vacancyList.isEmpty()) {
            throw new VacancyNotFoundException("Vacancy not found by area countryName: " + countryName);
        } else {
            return vacancyList;
        }
    }

    @Transactional
    public void createVacancy(Vacancy vacancy) {
        Long areaHeadHunterId = vacancy.getArea().getHeadHunterId();
        Area area = areaRepository.findByHeadHunterId(areaHeadHunterId)
                .orElseThrow(() -> new ResourceNotFoundException("Area not found with HeadHunterId: " + areaHeadHunterId));
        vacancy.setArea(area);
        Long employerHeadHunterId = vacancy.getEmployer().getHeadHunterId();
        Employer employer = employerRepository.findByHeadHunterId(employerHeadHunterId)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found with HeadHunterId: " + employerHeadHunterId));
        vacancy.setEmployer(employer);

        Vacancy savedVacancy = vacancyRepository.save(vacancy);

        area.addVacancy(savedVacancy);
        employer.addVacancy(savedVacancy);

    }

    @Transactional(readOnly = true)
    public boolean isAbsentByHeadHunterId(Vacancy vacancy) {
        Optional<Vacancy> vacancyByHeadHunterId = vacancyRepository.findByHeadHunterId(vacancy.getHeadHunterId());
        return vacancyByHeadHunterId.isEmpty();
    }

    @Transactional
    public void deleteAllVacancies() {
        vacancyRepository.deleteAll();
    }

    @Transactional
    public void deleteByCreatedDateBefore(LocalDateTime date) {
        vacancyRepository.deleteByCreatedDateBefore(date);
    }

    @Transactional
    public void deleteOlderThanWeek() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(7);
        vacancyRepository.deleteByCreatedDateBefore(localDateTime);
    }
}
