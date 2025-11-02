package by.savik.Jobbler.harvester.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDto {
    @JsonProperty("id")
    Long id;

    @JsonProperty("city")
    String city;

    @JsonProperty("street")
    String street;

    @Override
    public String toString() {
        return "AddressDto{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
