package by.savik.jobbler.repository;

import by.savik.jobbler.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    Optional<Vacancy> findByHeadHunterId(Long headHunterId);

    List<Vacancy> findByNameContainingIgnoreCase(String name);

    @Query("SELECT v FROM Vacancy v JOIN FETCH v.employer JOIN FETCH v.area")
    List<Vacancy> findAllWithEmployerAndArea();

    @Query("SELECT v FROM Vacancy v JOIN FETCH v.area WHERE UPPER(v.area.country) LIKE UPPER(:countryName)")
    List<Vacancy> findByAreaCountryNameIgnoreCase(@Param("countryName") String countryName);

    List<Vacancy> findByAddressCityContainingIgnoreCase(String addressCity);

    void deleteByCreatedDateBefore(LocalDateTime date);
}
