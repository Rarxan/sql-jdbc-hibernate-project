package com.javarush.service;

import com.javarush.dao.CityDAO;
import com.javarush.domain.City;

import java.util.List;

public class CityService {

    private final CityDAO cityDAO;

    public CityService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public List<City> getAllCities() {
        return cityDAO.getAllCities();
    }

    public City getCityById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("City id must be greater than zero");
        }
        return cityDAO.getCityById(id);
    }

    public List<City> getCitiesByCountryCode(String countryCode) {
        if (countryCode == null || countryCode.isBlank()) {
            throw new IllegalArgumentException("Country code must not be null or empty");
        }
        return cityDAO.getCitiesByCountryCode(countryCode);
    }

    public void saveCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City must not be null");
        }
        cityDAO.saveCity(city);
    }

    public void updateCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City must not be null");
        }
        cityDAO.updateCity(city);
    }

    public void deleteCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City must not be null");
        }
        cityDAO.deleteCity(city);
    }


}
