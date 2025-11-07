package by.savik.Jobbler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddressDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("city")
    private String city;

    @JsonProperty("street")
    private String street;

    @JsonProperty("areas")
    private List<AreaDto> areas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<AreaDto> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaDto> areas) {
        this.areas = areas;
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "headHunterId=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", areas=" + areas +
                '}';
    }
}
