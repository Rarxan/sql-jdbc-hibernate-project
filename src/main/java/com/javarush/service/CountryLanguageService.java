package com.javarush.service;

import com.javarush.exception.CountryNotFoundException;
import com.javarush.exception.LanguageNotFoundException;
import com.javarush.repository.CountryLanguageRepository;
import com.javarush.domain.CountryLanguage;

import java.util.List;

public class CountryLanguageService {

    private final CountryLanguageRepository languageDAO;

    public CountryLanguageService(CountryLanguageRepository languageDAO) {
        this.languageDAO = languageDAO;
    }

    public List<CountryLanguage> getAllLanguages() {
        return languageDAO.getAllLanguages();
    }

    public List<CountryLanguage> getLanguagesByCountryCode(String countryCode) {
        if (countryCode == null || countryCode.isBlank()) {
            throw new LanguageNotFoundException("Country code cannot be null or empty.");
        }
        List<CountryLanguage> languages = languageDAO.getLanguagesByCountryCode(countryCode);
        if (languages.isEmpty()) {
            throw new CountryNotFoundException("No languages found for country code: " + countryCode);
        }
        return languages;
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
