package com.cognizant.spring_learn.service;

import com.cognizant.spring_learn.Country;
import com.cognizant.spring_learn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    public List<Country> getAllCountries() {
        LOGGER.info("START");

        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("country.xml")) {

            List<Country> countries = context.getBean("countryList", List.class);

            LOGGER.debug("Countries: {}", countries);
            LOGGER.info("END");

            return countries;
        }
    }

    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START");

        List<Country> countries = getAllCountries();

        for (Country country : countries) {
            if (country.getCode().equalsIgnoreCase(code)) {
                LOGGER.debug("Country found: {}", country);
                LOGGER.info("END");
                return country;
            }
        }

        LOGGER.error("Country not found for code: {}", code);
        LOGGER.info("END");

        throw new CountryNotFoundException();
    }
}