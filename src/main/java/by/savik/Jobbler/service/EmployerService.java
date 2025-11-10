package by.savik.Jobbler.service;

import by.savik.Jobbler.entity.Employer;
import by.savik.Jobbler.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployerService implements EmployerServiceInterface {

    private final EmployerRepository employerRepository;

    @Autowired
    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public Employer createEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    @Transactional
    public boolean isPresentByHeadHunterId(Employer employer) {
        Optional<Employer> employerByHeadHunterId = employerRepository.findByHeadHunterId(employer.getHeadHunterId());
        return employerByHeadHunterId.isPresent();
    }
}
