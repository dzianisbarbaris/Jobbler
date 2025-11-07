package by.savik.Jobbler.entity;

import java.util.ArrayList;
import java.util.List;

public class Employer {


    private String id;

    private String name;

    private List<Vacancy> vacancies = new ArrayList<>();

    private String alternate_url;

    private int country_id;
}
