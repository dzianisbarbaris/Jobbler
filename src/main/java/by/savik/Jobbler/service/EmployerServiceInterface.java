package by.savik.Jobbler.service;

import by.savik.Jobbler.entity.Employer;

public interface EmployerServiceInterface {

    Employer createEmployer(Employer employer);

    boolean isPresentByHeadHunterId(Employer employer);

}
