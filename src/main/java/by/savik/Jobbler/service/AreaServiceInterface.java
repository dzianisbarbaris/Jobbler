package by.savik.Jobbler.service;

import by.savik.Jobbler.entity.Area;

public interface AreaServiceInterface {

    Area createArea(Area area);

    boolean isPresentByHeadHunterId(Area area);
}
