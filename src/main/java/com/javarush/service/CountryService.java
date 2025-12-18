package com.javarush.service;

import com.javarush.dao.CountryDAO;
import com.javarush.domain.Country;

import java.util.List;

public class CountryService {

    private final CountryDAO countryDAO;

    public CountryService(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    public List<Country> getAllCountries() {
        return countryDAO.getAllCountries();
    }

    public List<Country> getAllCountriesWithCitiesAndLanguages() {
        return countryDAO.getAllCountriesWithCitiesAndLanguages();
    }

    public Country getCountryByCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Country code must not be null or empty");
        }
        return countryDAO.getCountryByCode(code);
    }

    public void saveCountry(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country must not be null");
        }
        countryDAO.saveCountry(country);
    }

    public void updateCountry(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country must not be null");
        }
        countryDAO.updateCountry(country);
    }

    public void deleteCountry(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country must not be null");
        }
        countryDAO.deleteCountry(country);
    }
}
