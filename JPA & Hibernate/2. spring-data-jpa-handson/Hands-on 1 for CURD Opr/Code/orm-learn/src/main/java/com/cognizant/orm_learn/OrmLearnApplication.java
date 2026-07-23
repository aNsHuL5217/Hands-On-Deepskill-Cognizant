package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	private static CountryService countryService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = context.getBean(CountryService.class);

		testGetCountry();
		testAddCountry();
		testUpdateCountry();
		testSearchCountry();
		testDeleteCountry();
	}

	private static void testGetCountry() {
		LOGGER.info("Start get country");
		Country country = countryService.getCountry("IN");
		LOGGER.debug("Country: {}", country);
		LOGGER.info("End get country");
	}

	private static void testAddCountry() {
		LOGGER.info("Start add country");
		Country country = new Country();
		country.setCode("SG");
		country.setName("Singapore");
		countryService.addCountry(country);
		LOGGER.debug("Added Country: {}", country);
		LOGGER.info("End add country");
	}

	private static void testUpdateCountry() {
		LOGGER.info("Start update country");
		Country country = countryService.getCountry("SG");
		country.setName("Singapore Updated");
		countryService.updateCountry(country);
		LOGGER.debug("Updated Country: {}", country);
		LOGGER.info("End update country");
	}

	private static void testSearchCountry() {
		LOGGER.info("Start search country");
		List<Country> countries = countryService.searchCountry("in");
		LOGGER.debug("Search Result: {}", countries);
		LOGGER.info("End search country");
	}

	private static void testDeleteCountry() {
		LOGGER.info("Start delete country");
		countryService.deleteCountry("SG");
		LOGGER.info("Deleted country with code SG");
		LOGGER.info("End delete country");
	}
}