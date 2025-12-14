package by.savik.jobbler.repository;

import by.savik.jobbler.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

    Optional<Employer> findByHeadHunterId(Long headHunterId);

    List<Employer> findByNameContainingIgnoreCase(String name);

    @Query("SELECT e FROM Employer e")
    List<Employer> findAllWithVacancies();
}
