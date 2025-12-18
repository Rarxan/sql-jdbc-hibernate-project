package com.javarush.service;

import com.javarush.dao.CountryLanguageDAO;
import com.javarush.domain.CountryLanguage;

import java.util.List;

public class CountryLanguageService {

    private final CountryLanguageDAO languageDAO;

    public CountryLanguageService(CountryLanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }

    public List<CountryLanguage> getAllLanguages() {
        return languageDAO.getAllLanguages();
    }

    public List<CountryLanguage> getLanguagesByCountryCode(String countryCode) {
        if (countryCode == null || countryCode.isBlank()) {
            throw new IllegalArgumentException("Country code cannot be null or empty.");
        }
        return languageDAO.getLanguagesByCountryCode(countryCode);
    }

    public void saveLanguage(CountryLanguage language) {
        if (language == null) {
            throw new IllegalArgumentException("Language cannot be null.");
        }
        languageDAO.saveLanguage(language);
    }

    public void updateLanguage(CountryLanguage language) {
        if (language == null) {
            throw new IllegalArgumentException("Language cannot be null.");
        }
        languageDAO.updateLanguage(language);
    }

    public void deleteLanguage(CountryLanguage language) {
        if (language == null) {
            throw new IllegalArgumentException("Language cannot be null.");
        }
        languageDAO.deleteLanguage(language);
    }
}
