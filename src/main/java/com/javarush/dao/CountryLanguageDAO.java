package com.javarush.dao;

import com.javarush.domain.CountryLanguage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CountryLanguageDAO {

    private final SessionFactory sessionFactory;

    public CountryLanguageDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<CountryLanguage> getAllLanguages() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "select cl from CountryLanguage cl join fetch cl.country",
                    CountryLanguage.class
            ).getResultList();
        }
    }

    public List<CountryLanguage> getLanguagesByCountryId(int countryId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select cl from CountryLanguage cl join fetch cl.country where cl.country.id = :cid",
                            CountryLanguage.class
                    )
                    .setParameter("cid", countryId)
                    .getResultList();
        }
    }
}
