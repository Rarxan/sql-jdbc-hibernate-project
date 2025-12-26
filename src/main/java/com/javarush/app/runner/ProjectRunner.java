package com.javarush.app.runner;

import com.javarush.exception.CityNotFoundException;
import com.javarush.exception.CountryNotFoundException;
import com.javarush.exception.LanguageNotFoundException;
import com.javarush.repository.CityRepository;
import com.javarush.repository.CountryRepository;
import com.javarush.repository.CountryLanguageRepository;
import com.javarush.hibernate.HibernateUtil;
import com.javarush.service.CityService;
import com.javarush.service.CountryLanguageService;
import com.javarush.service.CountryService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectRunner {

    private static final Logger log = LoggerFactory.getLogger(ProjectRunner.class);

    private static final String DEFAULT_COUNTRY_CODE = "USA";

    private final CountryService countryService;
    private final CityService cityService;
    private final CountryLanguageService languageService;

    private ProjectRunner(CountryService countryService,
                          CityService cityService,
                          CountryLanguageService languageService) {
        this.countryService = countryService;
        this.cityService = cityService;
        this.languageService = languageService;
    }

    public static void start() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        ProjectRunner runner = new ProjectRunner(
                new CountryService(new CountryRepository(sessionFactory)),
                new CityService(new CityRepository(sessionFactory)),
                new CountryLanguageService(new CountryLanguageRepository(sessionFactory))
        );

        runner.run();

        HibernateUtil.shutdown();
    }

    private void run() {
        log.info("=== First 10 countries with cities and languages ===");

        countryService.getAllCountriesWithCitiesAndLanguages()
                .stream()
                .limit(10)
                .forEach(country -> {
                    if (country == null) return;
                    log.info("Country: {}", country.getName());

                    log.info(" Cities:");
                    country.getCities()
                            .forEach(city ->
                                    log.info("  - {}", city.getName())
                            );

                    log.info(" Languages:");
                    country.getLanguages()
                            .forEach(lang ->
                                    log.info("  - {}", lang.getLanguage())
                            );
                });

        log.info("=== First 10 cities in {} ===", DEFAULT_COUNTRY_CODE);

        try {
            cityService.getCitiesByCountryCode(DEFAULT_COUNTRY_CODE)
                    .stream()
                    .limit(10)
                    .forEach(city ->
                            log.info("{} -> {}", city.getName(), city.getCountry().getName())
                    );
        } catch (CityNotFoundException e) {
            log.error("Error fetching cities: {}", e.getMessage());
        }

        log.info("=== Languages in {} ===", DEFAULT_COUNTRY_CODE);

        try {
            languageService.getLanguagesByCountryCode(DEFAULT_COUNTRY_CODE)
                    .forEach(language ->
                            log.info("{} -> {}", language.getLanguage(), language.getCountry().getName())
                    );
        }catch (LanguageNotFoundException e){
            log.error("Error fetching languages: {}", e.getMessage());
        }

        log.info("=== Redis cache test ===");
        try {
            log.info(countryService.getCountryByCode(DEFAULT_COUNTRY_CODE).getName());
            log.info(countryService.getCountryByCode(DEFAULT_COUNTRY_CODE).getName());
        } catch (CountryNotFoundException e) {
            log.error("Error fetching country: {}", e.getMessage());
        }
    }
}
