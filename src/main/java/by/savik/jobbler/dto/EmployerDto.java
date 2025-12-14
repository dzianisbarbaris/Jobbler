package by.savik.jobbler.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployerDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("alternate_url")
    private String alternateUrl;

    @JsonProperty("country_id")
    private Long countryId;
}
