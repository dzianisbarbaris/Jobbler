package by.savik.Jobbler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class AreaDto {
    @JsonProperty("id")
    @Getter
    @Setter
    private Long id;

    @JsonProperty("parent_id")
    @Getter
    @Setter
    private String parent_id;

    @JsonProperty("name")
    @Getter
    @Setter
    private String name;

    @JsonProperty("areas")
    @Getter
    @Setter
    private List<AreaDto> areas;

    @Getter
    @Setter
    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AreaDto> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaDto> areas) {
        this.areas = areas;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

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
