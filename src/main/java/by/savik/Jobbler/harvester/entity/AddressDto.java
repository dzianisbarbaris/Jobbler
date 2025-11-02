package by.savik.Jobbler.harvester.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressDto {
    @JsonProperty("city")
    String city;

    @JsonProperty("street")
    String street;

    @JsonProperty("id")
    String id;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
