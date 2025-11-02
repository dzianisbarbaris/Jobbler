package by.savik.Jobbler.harvester.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class AreaDto {
    @JsonProperty("id")
    Long id;

    @JsonProperty("parent_id")
    String parent_id;

    @JsonProperty("name")
    String name;

    @JsonProperty("areas")
    ArrayList<AreaDto> areas;

    @Override
    public String toString() {
        return "AreaDto{" +
                "id='" + id + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", name='" + name + '\'' +
                ", areas=" + areas +
                '}';
    }
}
