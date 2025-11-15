package by.savik.jobbler.repository;

import by.savik.jobbler.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    Optional<Area> findByHeadHunterId(Long headHunterId);

    List<Area> findByNameContainingIgnoreCase(String name);

    List<Area> findByCountryContainingIgnoreCase(String name);
}
