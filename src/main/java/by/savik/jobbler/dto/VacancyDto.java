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
    private Date created_at;

    @JsonProperty("alternate_url")
    private String alternate_url;

    @JsonProperty("employer")
    private EmployerDto employer;

    public LocalDateTime dateMapper(){
        return this.created_at.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
