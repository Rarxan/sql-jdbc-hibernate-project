package com.javarush.app;

import com.javarush.dao.CityDAO;
import com.javarush.dao.CountryDAO;
import com.javarush.hibernate.HibernateUtil;
import com.javarush.service.CityService;
import com.javarush.service.CountryService;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        CountryService countryService = new CountryService(new CountryDAO(sessionFactory));

        CityService cityService = new CityService(new CityDAO(sessionFactory));

        countryService.getAllCountriesWithCitiesAndLanguages()
                .stream()
                .limit(10)
                .forEach(country -> {
                    System.out.println("Country: " + country.getName());

                    System.out.println(" Cities:");
                    country.getCities().forEach(city ->
                            System.out.println(" - " + city.getName()));

                    System.out.println(" Languages:");
                    country.getLanguages().forEach(lang ->
                            System.out.println(" - " + lang.getLanguage()));

                    System.out.println();
                });

        cityService.getCitiesByCountryCode("USA").stream().limit(10).forEach(city ->
                System.out.println(city.getName() + " -> " + city.getCountry().getName()));


        HibernateUtil.shutdown();
    }
}