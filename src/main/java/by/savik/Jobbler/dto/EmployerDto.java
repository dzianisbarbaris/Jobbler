package by.savik.Jobbler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployerDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("alternate_url")
    private String alternate_url;

    @JsonProperty("country_id")
    private Long country_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlternate_url() {
        return alternate_url;
    }

    public void setAlternate_url(String alternate_url) {
        this.alternate_url = alternate_url;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

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
