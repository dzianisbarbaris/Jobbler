package by.savik.Jobbler.service;

import by.savik.Jobbler.entity.Area;
import by.savik.Jobbler.entity.Employer;
import by.savik.Jobbler.entity.Vacancy;
import by.savik.Jobbler.exception.ResourceNotFoundException;
import by.savik.Jobbler.repository.AreaRepository;
import by.savik.Jobbler.repository.EmployerRepository;
import by.savik.Jobbler.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VacancyService implements VacancyServiceInterface{

    private final VacancyRepository vacancyRepository;
    private final AreaRepository areaRepository;
    private final EmployerRepository employerRepository;

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository, AreaRepository areaRepository, EmployerRepository employerRepository) {
        this.vacancyRepository = vacancyRepository;
        this.areaRepository = areaRepository;
        this.employerRepository = employerRepository;
    }

    @Transactional
    public Vacancy createVacancy(Vacancy vacancy){
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

        return savedVacancy;
    }

    @Transactional
    public boolean isPresentByHeadHunterId(Vacancy vacancy){
        Optional<Vacancy> vacancyByHeadHunterId = vacancyRepository.findByHeadHunterId(vacancy.getHeadHunterId());
        return vacancyByHeadHunterId.isPresent();
    }
}
