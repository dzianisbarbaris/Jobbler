package by.savik.Jobbler.harvester.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployerDto {
    @JsonProperty("id")
    String id;

    @JsonProperty("name")
    String name;

    @JsonProperty("alternate_url")
    String alternate_url;

    @JsonProperty("country_id")
    int country_id;

    @Override
    public String toString() {
        return "Employer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", alternate_url='" + alternate_url + '\'' +
                ", country_id=" + country_id +
                '}';
    }
}
