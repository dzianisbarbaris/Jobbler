package by.savik.Jobbler.service;

import by.savik.Jobbler.dto.AreaDto;
import by.savik.Jobbler.entity.Area;
import by.savik.Jobbler.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AreaService implements AreaServiceInterface {

    private final AreaRepository areaRepository;

    @Autowired
    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Transactional
    public Area createArea(Area area) {
        return areaRepository.save(area);
    }

    @Transactional
    public boolean isPresentByHeadHunterId(Area area) {
        Optional<Area> areaByHeadHunterId = areaRepository.findByHeadHunterId(area.getHeadHunterId());
        return areaByHeadHunterId.isPresent();
    }
}
