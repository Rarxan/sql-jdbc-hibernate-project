package com.javarush.service;

import com.javarush.domain.Country;
import com.javarush.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CountryRepositoryTest {

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
    void testGetAllCountries() {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Query<Country> query = session.createQuery("from Country", Country.class);
            assertNotNull(query, "Query should not be null");

            List<Country> countries = query.list();
            assertNotNull(countries, "Countries list should not be null");
            assertFalse(countries.isEmpty(), "Countries list should not be empty");

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            fail("Exception during testGetAllCountries: " + e.getMessage());
        }
    }

    @Test
    void testGetCountryByCode() {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String code = "USA";
            Query<Country> query = session.createQuery(
                    "from Country c where c.code = :code", Country.class);
            assertNotNull(query, "Query should not be null");

            query.setParameter("code", code);
            Country country = query.uniqueResult();
            assertNotNull(country, "Country should not be null");
            assertEquals(code, country.getCode(), "Country code should match");

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            fail("Exception during testGetCountryByCode: " + e.getMessage());
        }
    }
}