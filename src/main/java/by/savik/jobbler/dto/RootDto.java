package by.savik.jobbler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;


@Data
public class RootDto {
    @JsonProperty("items")
    private List<VacancyDto> items;

    @JsonProperty("found")
    private int found;

    @JsonProperty("pages")
    private int pages;

    @JsonProperty("page")
    private int page;

    @JsonProperty("per_page")
    private int per_page;
}