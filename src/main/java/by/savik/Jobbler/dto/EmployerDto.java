package by.savik.Jobbler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployerDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("alternate_url")
    private String alternate_url;

    @JsonProperty("country_id")
    private Long country_id;

    @Override
    public String toString() {
        return "EmployerDto{" +
                "headHunterId=" + id +
                ", name='" + name + '\'' +
                ", alternate_url='" + alternate_url + '\'' +
                ", country_id=" + country_id +
                '}';
    }
}
