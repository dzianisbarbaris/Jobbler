package by.savik.jobbler.service;

import by.savik.jobbler.dto.AreaDto;
import by.savik.jobbler.dto.VacancyDto;
import by.savik.jobbler.entity.Area;
import by.savik.jobbler.entity.Vacancy;

import java.util.List;

public interface DtoConverterServiceInterface {

    Area convertAreaDtoToArea(AreaDto areaDto);

    List<Area> convertAllDtoToAreas(List<AreaDto> dtoList);

    Vacancy convertVacancyDtoToVacancy(VacancyDto vacancyDto);

    List<Vacancy> convertAllDtoToVacancy(List<VacancyDto> dtoList);

}
