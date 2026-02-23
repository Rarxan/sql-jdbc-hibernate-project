package com.javarush.domain;

import jakarta.persistence.*;

import org.hibernate.annotations.Type;

import java.math.BigDecimal;

@Entity
@Table(name = "country_language")
public class CountryLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "language")
    private String language;

    @Column(name = "is_official", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isOfficial;

    @Column(name = "percentage")
    private BigDecimal percentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;

    public CountryLanguage() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public String getLanguage() {
        return language;
    }

    public Boolean getIsOfficial() {
        return isOfficial;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public Country getCountry() {
        return country;
    }
}