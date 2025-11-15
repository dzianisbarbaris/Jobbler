package by.savik.jobbler.controller;

import by.savik.jobbler.entity.Vacancy;
import by.savik.jobbler.service.VacancyServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/vacancy")
@Tag(name = "Vacancies Management", description = "APIs for managing vacancies")
public class VacancyController {

    private final VacancyServiceInterface vacancyService;

    @Autowired
    public VacancyController(VacancyServiceInterface vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping
    @Operation(summary = "Get all vacancies", description = "Retrieve a list of all vacancies with their employers and areas information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Vacancy>> getAllVacancies() {
        List<Vacancy> vacancies = vacancyService.getAllVacancies();
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/getByKeyWord")
    @Operation(summary = "Get all vacancies by KeyWord", description = "Retrieve a list of vacancies by KeyWord with their employers and areas information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "404", description = "Vacancy not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Vacancy>> getVacanciesByKeyword(@Parameter(description = "keyWord", required = true)
                                                               @RequestParam String keyWord) {
        List<Vacancy> vacancies = vacancyService.getVacanciesByName(keyWord);
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/getByAddressCity")
    @Operation(summary = "Get all vacancies by City Address", description = "Retrieve a list of vacancies by City Address with their employers and areas information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "404", description = "Vacancy not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Vacancy>> getVacanciesByAddressCity(@Parameter(description = "addressCity", required = true)
                                                               @RequestParam String addressCity) {
        List<Vacancy> vacancies = vacancyService.getVacanciesByAddressCity(addressCity);
        return ResponseEntity.ok(vacancies);
    }

    @GetMapping("/getByCountry")
    @Operation(summary = "Get all vacancies by Country Name", description = "Retrieve a list of vacancies by Country Name with their employers and areas information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "404", description = "Vacancy not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Vacancy>> getVacanciesByAreaCountryName(@Parameter(description = "countryName", required = true)
                                                                   @RequestParam String countryName) {
        List<Vacancy> vacancies = vacancyService.getVacanciesByAreaCountryName(countryName);
        return ResponseEntity.ok(vacancies);
    }

    @DeleteMapping
    @Operation(summary = "Delete all vacancies", description = "Delete All vacancies from DataBase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vacancies deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteAllVacancies() {
        vacancyService.deleteAllVacancies();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteByDate")
    @Operation(summary = "Delete vacancies by date", description = "Delete vacancies from DataBase by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vacancies deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteVacanciesByDate(@Parameter(description = "Date ISO format", required = true)
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        vacancyService.deleteByCreatedDateBefore(date);
        return ResponseEntity.noContent().build();
    }
}
