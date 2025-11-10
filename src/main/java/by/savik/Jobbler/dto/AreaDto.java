package by.savik.Jobbler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class AreaDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("parent_id")
    private String parent_id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("areas")
    private List<AreaDto> areas;

    private String country;

    @Override
    public String toString() {
        return "AreaDto{" +
                "headHunterId=" + id +
                ", parent_id='" + parent_id + '\'' +
                ", name='" + name + '\'' +
                ", areas=" + areas +
                ", country='" + country + '\'' +
                '}';
    }
}
