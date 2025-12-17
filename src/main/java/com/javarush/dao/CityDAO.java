package com.javarush.dao;

import com.javarush.domain.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            City city = session.get(City.class, id);
            session.getTransaction().commit();
            return city;
        }
    }
}
