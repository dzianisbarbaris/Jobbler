package by.savik.Jobbler.repository;

import by.savik.Jobbler.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    Optional<Vacancy> findByHeadHunterId(Long headHunterId);
}
