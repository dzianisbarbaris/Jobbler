package by.savik.jobbler.controller;

import by.savik.jobbler.entity.Area;
import by.savik.jobbler.service.AreaServiceInterface;
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
@RequestMapping("/api/area")
@Tag(name = "Area Management", description = "APIs for managing areas")
public class AreaController {
    private final AreaServiceInterface areaService;

    @Autowired
    public AreaController(AreaServiceInterface areaService) {
        this.areaService = areaService;
    }

    @GetMapping
    @Operation(summary = "Get all areas", description = "Retrieve a list of all areas with their vacancies information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of areas"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Area>> getAllAreas() {
        List<Area> areas = areaService.getAllAreas();
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/getByName")
    @Operation(summary = "Get all areas by name", description = "Retrieve a list of areas by name with their vacancies information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of areas"),
            @ApiResponse(responseCode = "404", description = "Areas not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Area>> getAreasByKeyWord(@Parameter(description = "Area Name", required = true)
                                                        @RequestParam String name) {
        List<Area> areas = areaService.getAreasByName(name);
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/getByCountry")
    @Operation(summary = "Get all areas by Country", description = "Retrieve a list of areas by country name with their vacancies information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of areas"),
            @ApiResponse(responseCode = "404", description = "Areas not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Area>> getAreasByCountryName(@Parameter(description = "Country Name", required = true)
                                                            @RequestParam String name) {
        List<Area> areas = areaService.getAreasByCountryName(name);
        return ResponseEntity.ok(areas);
    }
}
