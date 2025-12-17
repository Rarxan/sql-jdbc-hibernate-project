package com.javarush.app;

import com.javarush.dao.CityDAO;
import com.javarush.dao.CountryDAO;
import com.javarush.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CountryDAO countryDAO = new CountryDAO(sessionFactory);

        countryDAO.getAllCountriesWithCitiesAndLanguages()
                .stream()
                .limit(3)
                .forEach(country -> {
                    System.out.println("Country: " + country.getName());

                    System.out.println(" Cities:");
                    country.getCities().forEach(city ->
                            System.out.println("  - " + city.getName())
                    );

                    System.out.println(" Languages:");
                    country.getLanguages().forEach(lang ->
                            System.out.println("  - " + lang.getLanguage())
                    );

                    System.out.println();
                });

        CityDAO cityDAO = new CityDAO(sessionFactory);

        cityDAO.getCitiesByCountryCode("USA")
                .stream()
                .limit(5)
                .forEach(city ->
                        System.out.println(
                                city.getName() +
                                        " -> " +
                                        city.getCountry().getName()
                        )
                );

        HibernateUtil.shutdown();
    }
}