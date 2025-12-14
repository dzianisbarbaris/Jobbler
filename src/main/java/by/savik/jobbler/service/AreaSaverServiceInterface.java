package by.savik.jobbler.service;
import by.savik.jobbler.entity.Area;
import java.util.List;

public interface AreaSaverServiceInterface {

    List<Area> convertDtoToAreaAndSave(Long id, String countryName);

}
