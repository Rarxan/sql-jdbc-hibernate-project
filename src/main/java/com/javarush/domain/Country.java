package com.javarush.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "code_2")
    private String alternativeCode;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "continent")
    private Continent continent;

    @Column(name = "region")
    private String region;

    @Column(name = "surface_area")
    private BigDecimal surfaceArea;

    @Column(name = "indep_year")
    private Short independenceYear;

    @Column(name = "population")
    private Integer population;

    @Column(name = "life_expectancy")
    private BigDecimal lifeExpectancy;

    @Column(name = "gnp")
    private BigDecimal gnp;

    @Column(name = "gnpo_id")
    private BigDecimal gnpoId;

    @Column(name = "local_name")
    private String localName;

    @Column(name = "government_form")
    private String governmentForm;

    @Column(name = "head_of_state")
    private String headOfState;

    @Column(name = "capital")
    private Integer capital;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<City> cities = new ArrayList<City>();

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<CountryLanguage> languages = new ArrayList<>();

    public Country() {
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getAlternativeCode() {
        return alternativeCode;
    }

    public String getName() {
        return name;
    }

    public Continent getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }

    public BigDecimal getSurfaceArea() {
        return surfaceArea;
    }

    public Short getIndependenceYear() {
        return independenceYear;
    }

    public Integer getPopulation() {
        return population;
    }

    public BigDecimal getLifeExpectancy() {
        return lifeExpectancy;
    }

    public BigDecimal getGnp() {
        return gnp;
    }

    public BigDecimal getGnpoId() {
        return gnpoId;
    }

    public String getLocalName() {
        return localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public Integer getCapital() {
        return capital;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<CountryLanguage> getLanguages() {
        return languages;
    }
}