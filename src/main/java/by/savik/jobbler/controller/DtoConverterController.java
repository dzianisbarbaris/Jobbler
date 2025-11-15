package by.savik.jobbler.controller;

import by.savik.jobbler.dto.AreaDto;
import by.savik.jobbler.dto.VacancyDto;
import by.savik.jobbler.entity.Area;
import by.savik.jobbler.entity.Vacancy;
import by.savik.jobbler.service.*;
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
@Tag(name = "Vacancy/Area Converter Management", description = "APIs for converting DTO to Vacancy // DTO to Area ")
public class DtoConverterController {

    private final AreaServiceInterface areaService;
    private final EmployerServiceInterface employerService;
    private final VacancyServiceInterface vacancyService;
    private final DtoConverterServiceInterface dtoConverterService;
    private final HeadHunterServiceInterface headHunterService;

    @Autowired
    public DtoConverterController(AreaServiceInterface areaService, EmployerServiceInterface employerService, VacancyServiceInterface vacancyService, DtoConverterServiceInterface vacancyConverterService, HeadHunterServiceInterface headHunterService) {
        this.areaService = areaService;
        this.employerService = employerService;
        this.vacancyService = vacancyService;
        this.dtoConverterService = vacancyConverterService;
        this.headHunterService = headHunterService;
    }

    @PostMapping("/vacancies")
    @Operation(summary = "Create a new vacancies", description = "Create a new vacancies by keyWord and post them to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Vacancy>> convertDtoToVacancyAndSave(
            @Parameter(description = "keyWord", required = true)
            @RequestParam String keyWord) {
        List<VacancyDto> vacancyDtoList = headHunterService.getVacancyListFromHeadHunter(keyWord);
        List<Vacancy> vacancyList = dtoConverterService.convertAllDtoToVacancy(vacancyDtoList);
        for (Vacancy vacancy : vacancyList) {
            if (vacancyService.isAbsentByHeadHunterId(vacancy)) {
                if (areaService.isAbsentByHeadHunterId(vacancy.getArea())) {
                    areaService.createArea(vacancy.getArea());
                }
                if (employerService.isAbsentByHeadHunterId(vacancy.getEmployer())) {
                    employerService.createEmployer(vacancy.getEmployer());
                }
                vacancyService.createVacancy(vacancy);
            }
        }
        return ResponseEntity.ok(vacancyList);
    }

    @PostMapping("/areas")
    @Operation(summary = "Create a new areas", description = "Create a new areas and post them to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of areas"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Area>> convertDtoToAreaAndSave(
            @Parameter(description = "Area HeadHunter ID", required = true)
            @RequestParam Long id,
            @Parameter(description = "Country Name", required = true)
            @RequestParam String countryName) {
        List<AreaDto> areaDtoList = headHunterService.getAreaByHeadHunterIdAndSetCountry(id, countryName);
        List<Area> areaList = dtoConverterService.convertAllDtoToAreas(areaDtoList);
        for (Area area : areaList) {
            if (areaService.isAbsentByHeadHunterId(area)) {
                areaService.createArea(area);
            }
        }
        return ResponseEntity.ok(areaList);
    }
}
