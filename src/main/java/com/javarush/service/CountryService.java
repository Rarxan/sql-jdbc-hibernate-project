package com.javarush.service;

import com.javarush.exception.CountryNotFoundException;
import com.javarush.repository.CountryRepository;
import com.javarush.domain.Country;
import com.javarush.redis.CountryCache;

import java.util.List;

public class CountryService {

    private final CountryRepository countryRepository;

    private final CountryCache countryCache = new CountryCache();

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.getAllCountries();
    }

    public List<Country> getAllCountriesWithCitiesAndLanguages() {
        return countryRepository.getAllCountriesWithCitiesAndLanguages();
    }

    public Country getCountryByCode(String code) {
        if (code == null || code.isBlank()) {
            throw new CountryNotFoundException("Country code must not be null or empty");
        }

        Country cached = countryCache.get(code);
        if (cached != null) {
            return cached;
        }

        Country country = countryRepository.getCountryByCode(code);
        if (country == null) {
            throw  new CountryNotFoundException("Country with code " + code + " not found");
        }
        countryCache.put(code, country);
        return country;
    }

    public void saveCountry(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country must not be null");
        }
        countryRepository.saveCountry(country);
    }

    public void updateCountry(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country must not be null");
        }
        countryRepository.updateCountry(country);
    }

    public void deleteCountry(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country must not be null");
        }
        countryRepository.deleteCountry(country);
    }
}
