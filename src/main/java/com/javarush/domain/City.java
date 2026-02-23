package com.javarush.domain;

import jakarta.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;

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

    public Country getCountry() {
        return country;
    }
}
