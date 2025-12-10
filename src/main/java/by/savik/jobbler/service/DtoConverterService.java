package by.savik.jobbler.service;

import by.savik.jobbler.dto.AddressDto;
import by.savik.jobbler.dto.AreaDto;
import by.savik.jobbler.dto.VacancyDto;
import by.savik.jobbler.entity.Area;
import by.savik.jobbler.entity.Employer;
import by.savik.jobbler.entity.Vacancy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DtoConverterService implements DtoConverterServiceInterface {

    public Area convertAreaDtoToArea(AreaDto areaDto) {
        return new Area(areaDto.getId(), areaDto.getName(), areaDto.getCountry());
    }

    public List<Area> convertAllDtoToAreas(List<AreaDto> dtoList) {
        List<Area> areaList = new ArrayList<>();
        for (AreaDto dto : dtoList) {
            areaList.add(convertAreaDtoToArea(dto));
        }
        return areaList;
    }

    public Vacancy convertVacancyDtoToVacancy(VacancyDto vacancyDto) {
        if (vacancyDto.getArea() == null) {
            throw new IllegalArgumentException("Area cannot be null in VacancyDto");
        }
        if (vacancyDto.getEmployer() == null) {
            throw new IllegalArgumentException("Employer cannot be null in VacancyDto");
        }
        if (vacancyDto.getCreatedAt() == null) {
            throw new IllegalArgumentException("CreatedAt cannot be null in VacancyDto");
        }
        return new Vacancy(
                vacancyDto.getId(),
                vacancyDto.getName(),
                Optional.ofNullable(vacancyDto.getAddress()).map(AddressDto::getCity).orElse(null),
                Optional.ofNullable(vacancyDto.getAddress()).map(AddressDto::getStreet).orElse(null),
                vacancyDto.dateMapper(),
                vacancyDto.getAlternateUrl(),
                new Area(vacancyDto.getArea().getId(), vacancyDto.getArea().getName()),
                new Employer(vacancyDto.getEmployer().getId(), vacancyDto.getEmployer().getName(), vacancyDto.getEmployer().getAlternateUrl())
        );
    }

    public List<Vacancy> convertAllDtoToVacancy(List<VacancyDto> dtoList) {
        List<Vacancy> vacancyList = new ArrayList<>();
        for (VacancyDto dto : dtoList) {
            vacancyList.add(convertVacancyDtoToVacancy(dto));
        }
        return vacancyList;
    }
}
