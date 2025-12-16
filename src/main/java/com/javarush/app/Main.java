package com.javarush.app;

import com.javarush.dao.CityDAO;
import com.javarush.dao.CountryDAO;
import com.javarush.domain.City;
import com.javarush.domain.Country;
import com.javarush.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        CityDAO cityDAO = new CityDAO(sessionFactory);
        CountryDAO countryDAO = new CountryDAO(sessionFactory);

        System.out.println("===Cities===");
        List<City> cities = cityDAO.getAllCities();
        cities.stream().limit(10).forEach(city ->
                System.out.println(city.getId() + " " + city.getName()));

        System.out.println("\n===Country===");
        List<Country> countries = countryDAO.getAllCountries();
        countries.stream().limit(10).forEach(country ->
                System.out.println(country.getId() + " " + country.getName()));

        HibernateUtil.shutdown();

    }
}
