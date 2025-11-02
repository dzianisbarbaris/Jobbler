package by.savik.Jobbler.database.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Employer {


    private String id;

    private String name;

    private List<Item> items = new ArrayList<>();

    private String alternate_url;

    private int country_id;

    public Employer() {
    }

    public Employer(String name, List<Item> items, String alternate_url, int country_id) {
        this.name = name;
        this.items = items;
        this.alternate_url = alternate_url;
        this.country_id = country_id;
    }

    public String getId() {
        return id;
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

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", items=" + items +
                ", alternate_url='" + alternate_url + '\'' +
                ", country_id=" + country_id +
                '}';
    }
}
