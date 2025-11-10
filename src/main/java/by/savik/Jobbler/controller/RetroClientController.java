package by.savik.Jobbler.controller;

import by.savik.Jobbler.dto.AreaDto;
import by.savik.Jobbler.dto.VacancyDto;
import by.savik.Jobbler.service.RetroClientServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//TODO назвать VacancyController а то у тебя в api вылазит реализация(retro) кому какое дело как у тебя реализовано
@RestController
@RequestMapping("/api/vacancy")
@Tag(name = "HeadHunter API Management", description = "APIs for managing Headhunter APIs")
public class RetroClientController {

    private final RetroClientServiceInterface retroClientService;

    @Autowired
    public RetroClientController(RetroClientServiceInterface retroClientService) {
        this.retroClientService = retroClientService;
    }

    @GetMapping("/keyword")
    @Operation(summary = "Get vacancy list", description = "Get vacancy list by keyword from HeadHunter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<List<VacancyDto>> getVacancyListByKeyword(
            @Parameter(description = "KeyWord", required = true)
            @RequestParam String keyWord) {
        List<VacancyDto> vacancyListFromHeadHunter = retroClientService.getVacancyListFromHeadHunter(keyWord);
        return ResponseEntity.ok(vacancyListFromHeadHunter);
    }

    //TODO может отдельный контроллер сделать для area
    @GetMapping("/areas")
    @Operation(summary = "Get All areas list", description = "Get areas list from HeadHunter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of areas"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<List<AreaDto>> getAllAreasList() {
        List<AreaDto> allAreasList = retroClientService.getAllAreasList();
        return ResponseEntity.ok(allAreasList);
    }

    @GetMapping("/area")
    @Operation(summary = "Get area list by id", description = "Get area list by id from HeadHunter and set country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of areas"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<List<AreaDto>> getAreaByHeadHunterIdAndSetCountry(
            @Parameter(description = "Area ID", required = true)
            @RequestParam Long id,
            @Parameter(description = "Country name", required = true)
            @RequestParam String countryName) {
        List<AreaDto> areasByHeadHunterId = retroClientService.getAreaByHeadHunterIdAndSetCountry(id, countryName);
        return ResponseEntity.ok(areasByHeadHunterId);
    }

}
