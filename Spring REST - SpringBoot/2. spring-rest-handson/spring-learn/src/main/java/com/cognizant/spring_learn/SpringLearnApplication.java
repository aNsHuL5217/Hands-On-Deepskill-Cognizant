package com.cognizant.spring_learn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringLearnApplication.class, args);

		displayDate();
		displayCountry();
		displayCountries();
	}

	public static void displayDate() {
		LOGGER.info("START");

		try (ClassPathXmlApplicationContext context =
					 new ClassPathXmlApplicationContext("date-format.xml")) {

			SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);

			Date date = format.parse("31/12/2018");

			LOGGER.debug("Date : {}", date);

		} catch (ParseException e) {
			LOGGER.error("Date parsing failed", e);
		}

		LOGGER.info("END");
	}

	public static void displayCountry() {
		LOGGER.info("START");

		try (ClassPathXmlApplicationContext context =
					 new ClassPathXmlApplicationContext("country.xml")) {

			Country country = context.getBean("country", Country.class);
			Country anotherCountry = context.getBean("country", Country.class);

			LOGGER.debug("Country : {}", country);
			LOGGER.debug("Another Country : {}", anotherCountry);

			LOGGER.debug("country hashCode : {}", country.hashCode());
			LOGGER.debug("anotherCountry hashCode : {}", anotherCountry.hashCode());
		}

		LOGGER.info("END");
	}

	public static void displayCountries() {
		LOGGER.info("START");

		try (ClassPathXmlApplicationContext context =
					 new ClassPathXmlApplicationContext("country.xml")) {

			List<Country> countries = context.getBean("countryList", List.class);

			LOGGER.debug("Countries List : {}", countries);
		}

		LOGGER.info("END");
	}
}