package by.savik.Jobbler.harvester.controller;

import by.savik.Jobbler.harvester.entity.AreaDto;
import by.savik.Jobbler.harvester.entity.ItemDto;
import by.savik.Jobbler.harvester.service.RetroClientServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/api/retroClient")
@Tag(name = "HeadHunter API Management", description = "APIs for managing Headhunter APIs")
public class RetroClientController {

    private final RetroClientServiceInterface retroClientService;

    @Autowired
    public RetroClientController(RetroClientServiceInterface retroClientService) {
        this.retroClientService = retroClientService;
    }

    @GetMapping("/getByKeyword")
    @Operation(summary = "Get vacancy list", description = "Get vacancy list by keyword from HeadHunter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<List<ItemDto>> getVacancyListByKeyword(@RequestParam String keyWord){
        List<ItemDto> vacancyListFromHeadHunter = retroClientService.getVacancyListFromHeadHunter(keyWord);
        return ResponseEntity.ok(vacancyListFromHeadHunter);
    }

    @GetMapping("/getAllAreas")
    @Operation(summary = "Get area list", description = "Get area list from HeadHunter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of vacancies"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<List<AreaDto>> getAllAreasList(){
        List<AreaDto> allAreasList = retroClientService.getAllAreasList();
        return ResponseEntity.ok(allAreasList);
    }

}
