package com.javarush.repository;

import com.javarush.domain.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class CountryRepository {

    private final SessionFactory sessionFactory;

    public CountryRepository(SessionFactory sessionFactory) {
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

    public List<Country> getAllCountriesWithCitiesAndLanguages() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "select distinct c from Country c " +
                            "left join fetch c.cities " +
                            "left join fetch c.languages",
                    Country.class
            ).getResultList();
        }
    }

    public Country getCountryByCode(String code) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "select distinct c from Country c " +
                            "left join fetch c.cities " +
                            "left join fetch c.languages " +
                            "where c.code = :code",
                    Country.class
            ).setParameter("code", code).uniqueResult();
        }
    }

    public void saveCountry(Country country) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void updateCountry(Country country) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void deleteCountry(Country country) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
