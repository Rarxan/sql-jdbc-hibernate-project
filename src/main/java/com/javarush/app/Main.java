package com.javarush.app;

import com.javarush.dao.CityDAO;
import com.javarush.dao.CountryDAO;
import com.javarush.dao.CountryLanguageDAO;
import com.javarush.hibernate.HibernateUtil;
import com.javarush.service.CityService;
import com.javarush.service.CountryLanguageService;
import com.javarush.service.CountryService;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        CountryService countryService = new CountryService(new CountryDAO(sessionFactory));
        CityService cityService = new CityService(new CityDAO(sessionFactory));
        CountryLanguageService languageService = new CountryLanguageService(new CountryLanguageDAO(sessionFactory));

        System.out.println("=== First 10 countries with cities and languages ===");
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

        System.out.println("=== First 10 cities in USA ===");
        cityService.getCitiesByCountryCode("USA")
                .stream()
                .limit(10)
                .forEach(city ->
                        System.out.println(city.getName() + " -> " + city.getCountry().getName()));

        System.out.println("=== Languages in USA ===");
        languageService.getLanguagesByCountryCode("USA")
                .forEach(language ->
                        System.out.println(language.getLanguage() + " -> " + language.getCountry().getName()));

        System.out.println("=== Redis cache test ===");

        System.out.println(countryService.getCountryByCode("USA").getName());
        System.out.println(countryService.getCountryByCode("USA").getName());

        HibernateUtil.shutdown();
    }
}
