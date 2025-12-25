package com.javarush.app.runner;

import com.javarush.dao.CityDAO;
import com.javarush.dao.CountryDAO;
import com.javarush.dao.CountryLanguageDAO;
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

    // 🔹 ЕДИНАЯ ТОЧКА СТАРТА
    public static void start() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        ProjectRunner runner = new ProjectRunner(
                new CountryService(new CountryDAO(sessionFactory)),
                new CityService(new CityDAO(sessionFactory)),
                new CountryLanguageService(new CountryLanguageDAO(sessionFactory))
        );

        runner.run();

        HibernateUtil.shutdown();
    }

    // 🔹 ВСЯ ЛОГИКА ТУТ
    private void run() {
        log.info("=== First 10 countries with cities and languages ===");

        countryService.getAllCountriesWithCitiesAndLanguages()
                .stream()
                .limit(10)
                .forEach(country -> {
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
        cityService.getCitiesByCountryCode(DEFAULT_COUNTRY_CODE)
                .stream()
                .limit(10)
                .forEach(city ->
                        log.info("{} -> {}", city.getName(), city.getCountry().getName())
                );

        log.info("=== Languages in {} ===", DEFAULT_COUNTRY_CODE);
        languageService.getLanguagesByCountryCode(DEFAULT_COUNTRY_CODE)
                .forEach(language ->
                        log.info("{} -> {}", language.getLanguage(), language.getCountry().getName())
                );

        log.info("=== Redis cache test ===");
        log.info(countryService.getCountryByCode(DEFAULT_COUNTRY_CODE).getName());
        log.info(countryService.getCountryByCode(DEFAULT_COUNTRY_CODE).getName());
    }
}
