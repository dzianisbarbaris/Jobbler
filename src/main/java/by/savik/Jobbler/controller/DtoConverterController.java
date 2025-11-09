package by.savik.Jobbler.controller;

import by.savik.Jobbler.dto.AreaDto;
import by.savik.Jobbler.dto.VacancyDto;
import by.savik.Jobbler.entity.Area;
import by.savik.Jobbler.entity.Vacancy;
import by.savik.Jobbler.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/converter")
@Tag(name = "Converter Management", description = "APIs for converting DTO to Vacancy")
public class DtoConverterController {

    private final AreaServiceInterface areaService;
    private final EmployerServiceInterface employerService;
    private final VacancyServiceInterface vacancyService;
    private final DtoConverterServiceInterface dtoConverterService;
    private final RetroClientServiceInterface retroClientService;

    @Autowired
    public DtoConverterController(AreaServiceInterface areaService, EmployerServiceInterface employerService, VacancyServiceInterface vacancyService, DtoConverterServiceInterface vacancyConverterService, RetroClientServiceInterface retroClientService) {
        this.areaService = areaService;
        this.employerService = employerService;
        this.vacancyService = vacancyService;
        this.dtoConverterService = vacancyConverterService;
        this.retroClientService = retroClientService;
    }

    @PostMapping("/vacancies")
    @Operation(summary = "Create a new vacancies", description = "Create a new vacancies and post to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Vacancy>> convertDtoToVacancyAndSave(
            @Parameter(description = "KeyWord", required = true)
            @RequestParam String keyWord) {
        List<VacancyDto> vacancyDtoList = retroClientService.getVacancyListFromHeadHunter(keyWord);
        List<Vacancy> vacancyList = dtoConverterService.convertAllDtoToVacancy(vacancyDtoList);
        for (Vacancy vacancy : vacancyList) {
            if (!vacancyService.isPresentByHeadHunterId(vacancy)) {
                if (!areaService.isPresentByHeadHunterId(vacancy.getArea())) {
                    areaService.createArea(vacancy.getArea());
                }
                if (!employerService.isPresentByHeadHunterId(vacancy.getEmployer())) {
                    employerService.createEmployer(vacancy.getEmployer());
                }
                vacancyService.createVacancy(vacancy);
            }
        }
        return ResponseEntity.ok(vacancyList);
    }

    @PostMapping("/areas")
    @Operation(summary = "Create a new areas", description = "Create a new areas and post to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Area>> convertDtoToAreaAndSave(
            @Parameter(description = "Area HeadHunter ID", required = true)
            @RequestParam Long id,
            @Parameter(description = "Country name", required = true)
            @RequestParam String countryName) {
        List<AreaDto> areaDtoList = retroClientService.getAreaByHeadHunterIdAndSetCountry(id, countryName);
        List<Area> areaList = dtoConverterService.convertAllDtoToAreas(areaDtoList);
        for (Area area : areaList){
            if (!areaService.isPresentByHeadHunterId(area)){
                areaService.createArea(area);
            }
        }
        return ResponseEntity.ok(areaList);
    }


}
