package by.savik.Jobbler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class VacancyDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("area")
    private AreaDto area;

    @JsonProperty("address")
    private AddressDto address;

    @JsonProperty("created_at")
    private Date created_at;

    /*@JsonProperty("url")
    private String url;*/

    @JsonProperty("alternate_url")
    private String alternate_url;

    @JsonProperty("employer")
    private EmployerDto employer;

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

    public AreaDto getArea() {
        return area;
    }

    public void setArea(AreaDto area) {
        this.area = area;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getAlternate_url() {
        return alternate_url;
    }

    public void setAlternate_url(String alternate_url) {
        this.alternate_url = alternate_url;
    }

    public EmployerDto getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerDto employer) {
        this.employer = employer;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "headHunterId=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", address=" + address +
                ", created_at=" + created_at +
                ", alternate_url='" + alternate_url + '\'' +
                ", employer=" + employer +
                '}';
    }
}
