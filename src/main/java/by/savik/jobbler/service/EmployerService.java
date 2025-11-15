package by.savik.jobbler.service;

import by.savik.jobbler.entity.Employer;
import by.savik.jobbler.exception.EmployerNotFoundException;
import by.savik.jobbler.exception.VacancyNotFoundException;
import by.savik.jobbler.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerService implements EmployerServiceInterface {

    private final EmployerRepository employerRepository;

    @Autowired
    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Transactional
    public Employer createEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    @Transactional(readOnly = true)
    public List<Employer> getAllEmployers(){
        return employerRepository.findAllWithVacancies();
    }

    @Transactional(readOnly = true)
    public List<Employer> getEmployersByName(String name){
        List<Employer> employerList = employerRepository.findByNameContainingIgnoreCase(name);
        if (employerList.isEmpty()){
            throw new EmployerNotFoundException("Employer not found by name: " + name);
        } else {
            return employerList;
        }
    }

    @Transactional(readOnly = true)
    public boolean isAbsentByHeadHunterId(Employer employer) {
        Optional<Employer> employerByHeadHunterId = employerRepository.findByHeadHunterId(employer.getHeadHunterId());
        return employerByHeadHunterId.isEmpty();
    }
}
