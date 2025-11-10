package by.savik.Jobbler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
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