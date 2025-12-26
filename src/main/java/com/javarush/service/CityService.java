package com.javarush.service;

import com.javarush.exception.CityNotFoundException;
import com.javarush.repository.CityRepository;
import com.javarush.domain.City;

import java.util.List;

public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.getAllCities();
    }

    public City getCityById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("City id must be greater than zero");
        }
        return cityRepository.getCityById(id);
    }

    public List<City> getCitiesByCountryCode(String countryCode) {
        if (countryCode == null || countryCode.isBlank()) {
            throw new CityNotFoundException("Country code must not be null or empty");
        }
        List<City> cities = cityRepository.getCitiesByCountryCode(countryCode);
        if (cities.isEmpty()) {
            throw new CityNotFoundException("No cities found for country code: " + countryCode);
        }
        return cities;
    }

    public void saveCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City must not be null");
        }
        cityRepository.saveCity(city);
    }

    public void updateCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City must not be null");
        }
        cityRepository.updateCity(city);
    }

    public void deleteCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City must not be null");
        }
        cityRepository.deleteCity(city);
    }


}
