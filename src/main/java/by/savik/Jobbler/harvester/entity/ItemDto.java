package by.savik.Jobbler.harvester.entity;

import by.savik.Jobbler.database.entity.Address;
import by.savik.Jobbler.database.entity.Area;
import by.savik.Jobbler.database.entity.Employer;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class ItemDto {
    @JsonProperty("id")
    String id;

    @JsonProperty("name")
    String name;

    @JsonProperty("area")
    AreaDto area;

    @JsonProperty("address")
    AddressDto address;

    @JsonProperty("created_at")
    Date created_at;

    @JsonProperty("url")
    String url;

    @JsonProperty("alternate_url")
    String alternate_url;

    @JsonProperty("employer")
    EmployerDto employer;

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", address=" + address +
                ", created_at=" + created_at +
                ", url='" + url + '\'' +
                ", alternate_url='" + alternate_url + '\'' +
                ", employer=" + employer +
                '}';
    }
}
