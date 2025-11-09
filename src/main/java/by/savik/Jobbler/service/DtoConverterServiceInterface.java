package by.savik.Jobbler.service;

import by.savik.Jobbler.dto.AreaDto;
import by.savik.Jobbler.dto.VacancyDto;
import by.savik.Jobbler.entity.Area;
import by.savik.Jobbler.entity.Vacancy;

import java.util.List;

public interface DtoConverterServiceInterface {

    List<Area> convertAllDtoToAreas(List<AreaDto> dtoList);

    List<Vacancy> convertAllDtoToVacancy(List<VacancyDto> dtoList);

}
