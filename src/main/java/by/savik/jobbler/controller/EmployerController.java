package by.savik.jobbler.controller;

import by.savik.jobbler.entity.Employer;
import by.savik.jobbler.entity.Vacancy;
import by.savik.jobbler.service.EmployerServiceInterface;
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
@RequestMapping("/api/employer")
@Tag(name = "Employer Management", description = "APIs for managing employers")
public class EmployerController {

    private final EmployerServiceInterface employerService;

    @Autowired
    public EmployerController(EmployerServiceInterface employerService) {
        this.employerService = employerService;
    }

    @GetMapping
    @Operation(summary = "Get all employers", description = "Retrieve a list of all employers with their vacancies information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of employers"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Employer>> getAllEmployers() {
        List<Employer> employers = employerService.getAllEmployers();
        return ResponseEntity.ok(employers);
    }

    @GetMapping("/getByName")
    @Operation(summary = "Get all employers by KeyWord", description = "Retrieve a list of employers by KeyWord with their vacancies information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of employers"),
            @ApiResponse(responseCode = "404", description = "Employers not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Employer>> getEmployersByKeyword(@Parameter(description = "Employer Name", required = true)
                                                                @RequestParam String name) {
        List<Employer> employers = employerService.getEmployersByName(name);
        return ResponseEntity.ok(employers);
    }
}
