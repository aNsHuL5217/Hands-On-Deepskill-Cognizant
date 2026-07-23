package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Stock;
import com.cognizant.orm_learn.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	private static StockService stockService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

		stockService = context.getBean(StockService.class);

		testFacebookSeptemberStocks();
		testGoogleStocksAbove1250();
		testTop3HighestVolume();
		testNetflixLowestClose();
	}

	private static void testFacebookSeptemberStocks() {
		LOGGER.info("Facebook Stocks - September 2019");

		List<Stock> stocks = stockService.getFacebookSeptemberStocks();

		stocks.forEach(stock -> LOGGER.debug("{}", stock));
	}

	private static void testGoogleStocksAbove1250() {
		LOGGER.info("Google Stocks Close > 1250");

		List<Stock> stocks = stockService.getGoogleStocksAbove1250();

		stocks.forEach(stock -> LOGGER.debug("{}", stock));
	}

	private static void testTop3HighestVolume() {
		LOGGER.info("Top 3 Highest Volume");

		List<Stock> stocks = stockService.getTop3HighestVolume();

		stocks.forEach(stock -> LOGGER.debug("{}", stock));
	}

	private static void testNetflixLowestClose() {
		LOGGER.info("Netflix Lowest 3 Close Price");

		List<Stock> stocks = stockService.getNetflixLowest3Close();

		stocks.forEach(stock -> LOGGER.debug("{}", stock));
	}
}