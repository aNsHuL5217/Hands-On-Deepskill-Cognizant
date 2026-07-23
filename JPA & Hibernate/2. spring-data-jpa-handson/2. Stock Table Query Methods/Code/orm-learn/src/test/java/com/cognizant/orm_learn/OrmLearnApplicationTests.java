package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Stock;
import com.cognizant.orm_learn.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrmLearnApplicationTests {

	@Autowired
	private StockService stockService;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldGetFacebookStocksForSeptember2019() {
		List<Stock> stocks = stockService.getFacebookSeptemberStocks();

		assertThat(stocks).isNotEmpty();
		assertThat(stocks)
				.allMatch(stock -> stock.getCode().equals("FB"));
	}

	@Test
	void shouldGetGoogleStocksAbove1250() {
		List<Stock> stocks = stockService.getGoogleStocksAbove1250();

		assertThat(stocks).isNotEmpty();
		assertThat(stocks)
				.allMatch(stock -> stock.getCode().equals("GOOGL"));
	}

	@Test
	void shouldGetTop3HighestVolume() {
		List<Stock> stocks = stockService.getTop3HighestVolume();

		assertThat(stocks).hasSizeLessThanOrEqualTo(3);
		assertThat(stocks).isNotEmpty();
	}

	@Test
	void shouldGetNetflixLowest3ClosePrice() {
		List<Stock> stocks = stockService.getNetflixLowest3Close();

		assertThat(stocks).hasSizeLessThanOrEqualTo(3);
		assertThat(stocks)
				.allMatch(stock -> stock.getCode().equals("NFLX"));
	}
}