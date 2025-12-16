package com.javarush.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "city")
public class City {


    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "district")
    private String district;

    @Column(name = "population")
    private Integer population;

    public City() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public String getDistrict() {
        return district;
    }

    public Integer getPopulation() {
        return population;
    }
}
