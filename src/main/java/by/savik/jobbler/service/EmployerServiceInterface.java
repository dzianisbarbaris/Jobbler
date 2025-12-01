package by.savik.jobbler.service;

import by.savik.jobbler.entity.Employer;

import java.util.List;

public interface EmployerServiceInterface {

    void createEmployer(Employer employer);

    List<Employer> getAllEmployers();

    List<Employer> getEmployersByName(String name);

    boolean isAbsentByHeadHunterId(Employer employer);

}
