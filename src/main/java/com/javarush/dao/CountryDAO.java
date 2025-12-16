package com.javarush.dao;

import com.javarush.domain.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CountryDAO {

    private final SessionFactory sessionFactory;

    public CountryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Country> getAllCountries() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Country> countries = session.createQuery("from Country", Country.class).list();
            session.getTransaction().commit();
            return countries;
        }
    }
}
