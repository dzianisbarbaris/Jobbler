package by.savik.Jobbler.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "areas")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Area headHunterId must be greater than 0")
    @Column(nullable = false, unique = true)
    private Long headHunterId;

    @NotBlank(message = "Area name is required")
    @Size(max = 200, message = "Area name must be less than 200 characters")
    @Column(nullable = false, length = 200)
    private String name;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Vacancy> vacancies = new ArrayList<>();

    @Size(max = 100, message = "Country name must be less than 100 characters")
    @Column(length = 100)
    private String country;

    public Area() {
    }

    public Area(Long headHunterId, String name, String country) {
        this.headHunterId = headHunterId;
        this.name = name;
        this.country = country;
    }

    public Area(Long headHunterId, String name) {
        this.headHunterId = headHunterId;
        this.name = name;
    }

    public void addVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
        vacancy.setArea(this);
    }

    public Long getId() {
        return id;
    }

    public Long getHeadHunterId() {
        return headHunterId;
    }

    public void setHeadHunterId(Long headHunterId) {
        this.headHunterId = headHunterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", headHunterId=" + headHunterId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
