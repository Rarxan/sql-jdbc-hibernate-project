package com.javarush.dao;

import com.javarush.domain.CountryLanguage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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

    public List<CountryLanguage> getLanguagesByCountryCode(String countryCode) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select cl from CountryLanguage cl join fetch cl.country c where c.code = :code",
                            CountryLanguage.class
                    )
                    .setParameter("code", countryCode)
                    .getResultList();
        }
    }

    public void saveLanguage(CountryLanguage language) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(language);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void updateLanguage(CountryLanguage language) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(language);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void deleteLanguage(CountryLanguage language) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(language);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
