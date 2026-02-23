package com.javarush.service;

import com.javarush.domain.CountryLanguage;
import com.javarush.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CountryLanguageRepositoryTest {

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
    void testGetAllLanguages() {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Query<CountryLanguage> query = session.createQuery("from CountryLanguage", CountryLanguage.class);
            assertNotNull(query, "Query should not be null");

            List<CountryLanguage> languages = query.list();
            assertNotNull(languages, "Languages list should not be null");
            assertFalse(languages.isEmpty(), "Languages list should not be empty");

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            fail("Exception during testGetAllLanguages: " + e.getMessage());
        }
    }

    @Test
    void testGetLanguageByCountryCode() {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String countryCode = "USA";
            Query<CountryLanguage> query = session.createQuery(
                    "from CountryLanguage cl where cl.country.code = :code", CountryLanguage.class);
            assertNotNull(query, "Query should not be null");

            query.setParameter("code", countryCode);
            List<CountryLanguage> languages = query.list();
            assertNotNull(languages, "Languages list should not be null");
            assertFalse(languages.isEmpty(), "Languages list should not be empty");

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            fail("Exception during testGetLanguageByCountryCode: " + e.getMessage());
        }
    }
}
