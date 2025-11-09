package by.savik.Jobbler.service;

import by.savik.Jobbler.dto.AddressDto;
import by.savik.Jobbler.dto.AreaDto;
import by.savik.Jobbler.dto.VacancyDto;
import by.savik.Jobbler.entity.Area;
import by.savik.Jobbler.entity.Employer;
import by.savik.Jobbler.entity.Vacancy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DtoConverterService implements DtoConverterServiceInterface {

    public List<Area> convertAllDtoToAreas(List<AreaDto> dtoList) {
        List<Area> areaList = new ArrayList<>();
        for (AreaDto dto : dtoList) {
            Area area = new Area(dto.getId(), dto.getName(), dto.getCountry());
            areaList.add(area);
        }
        return areaList;
    }


    public List<Vacancy> convertAllDtoToVacancy(List<VacancyDto> dtoList) {
        List<Vacancy> vacancyList = new ArrayList<>();
        for (VacancyDto dto : dtoList) {
            Area area = new Area(dto.getArea().getId(), dto.getArea().getName());
            Employer employer = new Employer(dto.getEmployer().getId(), dto.getEmployer().getName(), dto.getEmployer().getAlternate_url());
            Optional<AddressDto> addressDto = Optional.ofNullable(dto.getAddress());
            if (addressDto.isPresent()) {
                Vacancy vacancy = new Vacancy(dto.getId(), dto.getName(), addressDto.get().getCity(), addressDto.get().getStreet(), dto.getCreated_at(), dto.getAlternate_url());

                vacancy.setEmployer(employer);
                vacancy.setArea(area);
                vacancyList.add(vacancy);
            }
        }
        return vacancyList;
    }


}
