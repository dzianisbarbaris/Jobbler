package by.savik.jobbler.service;

import by.savik.jobbler.entity.Area;

import java.util.List;

public interface AreaServiceInterface {

    List<Area> getAllAreas();

    List<Area> getAreasByName(String name);

    List<Area> getAreasByCountryName(String name);

    void createArea(Area area);

    boolean isAbsentByHeadHunterId(Area area);
}
