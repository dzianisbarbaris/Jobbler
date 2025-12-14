package by.savik.jobbler.controller;

import by.savik.jobbler.entity.Vacancy;
import by.savik.jobbler.service.VacancySaverServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vacancySaver")
@Tag(name = "Vacancy Saver Management", description = "APIs for converting DTO to Vacancy and save to database")
public class VacancySaverController {
    private final VacancySaverServiceInterface vacancySaver;

    @Autowired
    public VacancySaverController(VacancySaverServiceInterface vacancySaver) {
        this.vacancySaver = vacancySaver;
    }

    @PostMapping()
    @Operation(summary = "Create a new vacancies", description = "Create a new vacancies by keyWord and save them to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Vacancy>> convertDtoToVacancyAndSave(
            @Parameter(description = "keyWord", required = true)
            @RequestParam String keyWord) {
        List<Vacancy> vacancyList = vacancySaver.convertDtoToVacancyAndSave(keyWord);
        return ResponseEntity.ok(vacancyList);
    }

    @PostMapping("/saveDailyVacancies")
    @Operation(summary = "Create a new Daily vacancies", description = "Create a new daily list of vacancies and save them to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Vacancy>> convertDailyDtoToVacanciesAndSave() {
        List<Vacancy> vacancyList = vacancySaver.convertDailyDtoToVacanciesAndSave();
        return ResponseEntity.ok(vacancyList);
    }
}
