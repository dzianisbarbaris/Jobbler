package by.savik.Jobbler.repository;

import by.savik.Jobbler.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    Optional<Area> findByHeadHunterId(Long headHunterId);
}
