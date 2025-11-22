package by.savik.jobbler.service;

import by.savik.jobbler.dto.AreaDto;
import by.savik.jobbler.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaSaverService implements AreaSaverServiceInterface{

    private final AreaServiceInterface areaService;
    private final DtoConverterServiceInterface dtoConverterService;
    private final HeadHunterServiceInterface headHunterService;

    @Autowired
    public AreaSaverService(AreaServiceInterface areaService, DtoConverterServiceInterface dtoConverterService,
                            HeadHunterServiceInterface headHunterService) {
        this.areaService = areaService;
        this.dtoConverterService = dtoConverterService;
        this.headHunterService = headHunterService;
    }

    @Transactional
    public List<Area> convertDtoToAreaAndSave(Long id, String countryName){
        List<AreaDto> areaDtoList = headHunterService.getAreaByHeadHunterIdAndSetCountry(id, countryName);
        List<Area> areaList = dtoConverterService.convertAllDtoToAreas(areaDtoList);
        for (Area area : areaList) {
            if (areaService.isAbsentByHeadHunterId(area)) {
                areaService.createArea(area);
            }
        }
        return areaList;
    }
}
