package com.javarush.repository;

import com.javarush.domain.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CityDAO {

    private final SessionFactory sessionFactory;

    public CityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<City> getAllCities() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "select c from City c join fetch c.country",
                    City.class
            ).getResultList();
        }
    }

    public City getCityById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            City city = session.get(City.class, id);
            session.getTransaction().commit();
            return city;
        }
    }

    public List<City> getCitiesByCountryCode(String countryCode) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "select c from City c " +
                                    "join fetch c.country ct " +
                                    "where ct.code = :code",
                            City.class
                    ).setParameter("code", countryCode)
                    .getResultList();
        }
    }

    public void saveCity(City city) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(city);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            throw ex;
        }
    }

    public void updateCity(City city) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(city);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            throw ex;
        }
    }

    public void deleteCity(City city) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(city);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            throw ex;
        }
    }
}
