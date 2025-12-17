package com.javarush.app;

import com.javarush.dao.CountryLanguageDAO;
import com.javarush.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CountryLanguageDAO languageDAO = new CountryLanguageDAO(sessionFactory);

        System.out.println("===Languages with countries===");

        languageDAO.getAllLanguages()
                .stream()
                .limit(10)
                .forEach(lang ->
                        System.out.println(
                                lang.getLanguage() +
                                        " -> " +
                                        lang.getCountry().getName() +
                                        " (official: " + lang.getIsOfficial() + ")"
                        )
                );

        HibernateUtil.shutdown();
    }
}