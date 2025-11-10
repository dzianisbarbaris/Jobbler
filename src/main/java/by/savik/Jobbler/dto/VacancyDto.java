package by.savik.Jobbler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
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

    /*@JsonProperty("url")
    private String url;*/

    @JsonProperty("alternate_url")
    private String alternateUrl;

    @JsonProperty("employer")
    private EmployerDto employer;

    @Override
    public String toString() {
        return "ItemDto{" +
                "headHunterId=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", address=" + address +
                ", created_at=" + created_at +
                ", alternate_url='" + alternateUrl + '\'' +
                ", employer=" + employer +
                '}';
    }
}
