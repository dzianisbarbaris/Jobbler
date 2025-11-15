package by.savik.jobbler.service;

import by.savik.jobbler.entity.Employer;

import java.util.List;
import java.util.Optional;

public interface EmployerServiceInterface {

    Employer createEmployer(Employer employer);

    List<Employer> getAllEmployers();

    List<Employer> getEmployersByName(String name);

    boolean isAbsentByHeadHunterId(Employer employer);

}
