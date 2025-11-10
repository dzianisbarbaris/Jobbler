package by.savik.Jobbler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("city")
    private String city;

    @JsonProperty("street")
    private String street;


    @Override
    public String toString() {
        return "AddressDto{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
