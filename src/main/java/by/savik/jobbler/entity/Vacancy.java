package by.savik.jobbler.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "vacancies")
@Getter
@Setter
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Vacancy headHunterId must be greater than 0")
    @Column(nullable = false, unique = true)
    private Long headHunterId;

    @NotBlank(message = "Vacancy name is required")
    @Size(min = 2, max = 200, message = "Vacancy name must be between 2 and 200 characters")
    @Column(nullable = false, length = 200)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    @JsonBackReference
    private Area area;

    @Size(max = 200, message = "Vacancy AddressCity must be less than 200 characters")
    @Column(length = 200)
    private String addressCity;

    @Size(max = 200, message = "Vacancy AddressStreet must be less than 200 characters")
    @Column(length = 200)
    private String addressStreet;

    @DateTimeFormat
    private Date createdDate;

    @NotBlank(message = "Vacancy URL is required")
    @Size(min = 2, max = 100, message = "Vacancy URL must be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    @JsonBackReference
    private Employer employer;

    public Vacancy() {
    }

    public Vacancy(Long headHunterId, String name, String addressCity, String addressStreet, Date createdDate, String url) {
        this.headHunterId = headHunterId;
        this.name = name;
        this.addressCity = addressCity;
        this.addressStreet = addressStreet;
        this.createdDate = createdDate;
        this.url = url;
    }

    public Vacancy(Long headHunterId, String name, String addressCity, String addressStreet, Date createdDate, String url, Area area, Employer employer) {
        this.headHunterId = headHunterId;
        this.name = name;
        this.addressCity = addressCity;
        this.addressStreet = addressStreet;
        this.createdDate = createdDate;
        this.url = url;
        this.area = area;
        this.employer = employer;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", headHunterId=" + headHunterId +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", addressCity='" + addressCity + '\'' +
                ", addressStreet='" + addressStreet + '\'' +
                ", createdDate=" + createdDate +
                ", url='" + url + '\'' +
                ", employer=" + employer +
                '}';
    }
}
