package by.savik.jobbler.controller;

import by.savik.jobbler.dto.AreaDto;
import by.savik.jobbler.dto.VacancyDto;
import by.savik.jobbler.service.HeadHunterServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/headhunter")
@Tag(name = "HeadHunter API Management", description = "APIs for managing Headhunter APIs")
public class HeadHunterController {
    private final HeadHunterServiceInterface headHunterService;

    @Autowired
    public HeadHunterController(HeadHunterServiceInterface headHunterService) {
        this.headHunterService = headHunterService;
    }

    @GetMapping("/vacancy/keyword")
    @Operation(summary = "Get vacancy list", description = "Get vacancy list by keyword from HeadHunter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<VacancyDto>> getVacancyListByKeyword(
            @Parameter(description = "KeyWord", required = true)
            @RequestParam String keyWord) {
        List<VacancyDto> vacancyListFromHeadHunter = headHunterService.getVacancyListFromHeadHunter(keyWord);
        return ResponseEntity.ok(vacancyListFromHeadHunter);
    }

    @GetMapping("/area")
    @Operation(summary = "Get All areas list", description = "Get areas list from HeadHunter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of areas"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<AreaDto>> getAllAreasList() {
        List<AreaDto> allAreasList = headHunterService.getAllAreasList();
        return ResponseEntity.ok(allAreasList);
    }

    @GetMapping("/area/getByParentId")
    @Operation(summary = "Get area list by parent id", description = "Get area list by parent id from HeadHunter and set country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of areas"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<AreaDto>> getAreaByHeadHunterIdAndSetCountry(
            @Parameter(description = "Area ID", required = true)
            @RequestParam Long id,
            @Parameter(description = "Country name", required = true)
            @RequestParam String countryName) {
        List<AreaDto> areasByHeadHunterId = headHunterService.getAreaByHeadHunterIdAndSetCountry(id, countryName);
        return ResponseEntity.ok(areasByHeadHunterId);
    }
}
