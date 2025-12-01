package by.savik.jobbler.service;

import by.savik.jobbler.entity.Area;
import by.savik.jobbler.exception.AreaNotFoundException;
import by.savik.jobbler.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService implements AreaServiceInterface {
    private final AreaRepository areaRepository;

    @Autowired
    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Transactional(readOnly = true)
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Area> getAreasByName(String name) {
        List<Area> areaList = areaRepository.findByNameContainingIgnoreCase(name);
        if (areaList.isEmpty()) {
            throw new AreaNotFoundException("Area not found by name: " + name);
        } else {
            return areaList;
        }
    }

    @Transactional(readOnly = true)
    public List<Area> getAreasByCountryName(String name) {
        List<Area> areaList = areaRepository.findByCountryContainingIgnoreCase(name);
        if (areaList.isEmpty()) {
            throw new AreaNotFoundException("Area not found by country name: " + name);
        } else {
            return areaList;
        }
    }

    @Transactional
    public void createArea(Area area) {
        areaRepository.save(area);
    }

    @Transactional(readOnly = true)
    public boolean isAbsentByHeadHunterId(Area area) {
        Optional<Area> areaByHeadHunterId = areaRepository.findByHeadHunterId(area.getHeadHunterId());
        return areaByHeadHunterId.isEmpty();
    }
}
