package com.javarush.service;

import com.javarush.domain.City;
import com.javarush.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CityRepositoryTest {

    private Session session;

    @BeforeAll
    void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @AfterAll
    void tearDown() {
        if (session != null) {
            session.close();
        }
    }

    @Test
    void testGetAllCities() {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Query<City> query = session.createQuery("from City", City.class);
            assertNotNull(query, "Query should not be null");

            List<City> cities = query.list();
            assertNotNull(cities, "Cities list should not be null");
            assertFalse(cities.isEmpty(), "Cities list should not be empty");

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            fail("Exception during testGetAllCities: " + e.getMessage());
        }
    }

    @Test
    void testGetCityByName() {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String name = "New York";
            Query<City> query = session.createQuery(
                    "from City c where c.name = :name", City.class);
            assertNotNull(query, "Query should not be null");

            query.setParameter("name", name);
            City city = query.uniqueResult();
            assertNotNull(city, "City should not be null");
            assertEquals(name, city.getName(), "City name should match");

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            fail("Exception during testGetCityByName: " + e.getMessage());
        }
    }
}
