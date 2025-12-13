package by.savik.jobbler.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AreaDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("parent_id")
    private String parentId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("areas")
    private List<AreaDto> areas;

    private String country;
}
