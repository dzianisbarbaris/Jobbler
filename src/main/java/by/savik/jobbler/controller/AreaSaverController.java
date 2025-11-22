package by.savik.jobbler.controller;

import by.savik.jobbler.dto.AreaDto;
import by.savik.jobbler.entity.Area;
import by.savik.jobbler.service.AreaSaverServiceInterface;
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
@RequestMapping("/api/areaSaver")
@Tag(name = "Area Saver Management", description = "APIs for converting DTO to Area and save to database")
public class AreaSaverController {

    private final AreaSaverServiceInterface areaSaverService;

    @Autowired
    public AreaSaverController(AreaSaverServiceInterface areaSaverService) {
        this.areaSaverService = areaSaverService;
    }

    @PostMapping()
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
        List<Area> areaList = areaSaverService.convertDtoToAreaAndSave(id, countryName);
        return ResponseEntity.ok(areaList);
    }
}
