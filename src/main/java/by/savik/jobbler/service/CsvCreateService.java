package by.savik.jobbler.service;

import by.savik.jobbler.entity.Vacancy;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CsvCreateService implements CsvCreateServiceInterface {

    public File createCsvFile(List<Vacancy> vacancies, String keyword) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String safeKeyword = keyword.replaceAll("[^a-zA-Zа-яА-Я0-9]", "_");
        File csvFile = File.createTempFile("vacancies_" + safeKeyword + "_" + timestamp, ".csv");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.builder()
                     .setRecordSeparator("\n")
                     .build())) {

            csvPrinter.printRecord(
                    "ID", "Vacancy Name", "Vacancy URL", "Employer Name", "Employer URL",
                    "City", "Street", "Area", "Country", "Created Date"
            );

            for (Vacancy vacancy : vacancies) {
                csvPrinter.printRecord(
                        vacancy.getId(),
                        getOrEmpty(vacancy.getName()),
                        getOrEmpty(vacancy.getUrl()),
                        getOrEmpty(vacancy.getEmployer() != null ? vacancy.getEmployer().getName() : null),
                        getOrEmpty(vacancy.getEmployer() != null ? vacancy.getEmployer().getUrl() : null),
                        getOrEmpty(vacancy.getAddressCity()),
                        getOrEmpty(vacancy.getAddressStreet()),
                        getOrEmpty(vacancy.getArea() != null ? vacancy.getArea().getName() : null),
                        getOrEmpty(vacancy.getArea() != null ? vacancy.getArea().getCountry() : null),
                        getDateTimeOrEmpty(vacancy.getCreatedDate())
                );
            }
        }
        return csvFile;
    }

    private String getOrEmpty(String value) {
        return value != null ? value : "";
    }

    private String getDateTimeOrEmpty(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "";
    }
}
