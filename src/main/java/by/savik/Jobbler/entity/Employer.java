package by.savik.Jobbler.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employers")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Employer headHunterId must be greater than 0")
    @Column(nullable = false, unique = true)
    private Long headHunterId;

    @NotBlank(message = "Employer name is required")
    @Size(max = 200, message = "Employer name must be less than 200 characters")
    @Column(nullable = false, length = 200)
    private String name;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Vacancy> vacancies = new ArrayList<>();

    @NotBlank(message = "Employer URL is required")
    @Size(min = 2, max = 100, message = "Employer URL must be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String url;

    public Employer() {
    }

    public Employer(Long headHunterId, String name, String url) {
        this.headHunterId = headHunterId;
        this.name = name;
        this.url = url;
    }

    public void addVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
        vacancy.setEmployer(this);
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", headHunterId=" + headHunterId +
                ", name='" + name + '\'' +
                ", vacancies=" + vacancies +
                ", url='" + url + '\'' +
                '}';
    }
}
