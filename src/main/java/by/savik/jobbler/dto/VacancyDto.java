package by.savik.jobbler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Data
public class VacancyDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("area")
    private AreaDto area;

    @JsonProperty("address")
    private AddressDto address;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("alternate_url")
    private String alternateUrl;

    @JsonProperty("employer")
    private EmployerDto employer;

    public LocalDateTime dateMapper() {
        return this.createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
