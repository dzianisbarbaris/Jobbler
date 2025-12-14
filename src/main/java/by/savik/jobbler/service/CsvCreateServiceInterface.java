package by.savik.jobbler.service;

import by.savik.jobbler.entity.Vacancy;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CsvCreateServiceInterface {

    File createCsvFile(List<Vacancy> vacancies, String keyword) throws IOException;
}
