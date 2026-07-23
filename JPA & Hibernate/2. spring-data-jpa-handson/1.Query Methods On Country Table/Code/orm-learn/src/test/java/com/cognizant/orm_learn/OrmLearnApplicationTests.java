package com.cognizant.orm_learn;

import com.cognizant.orm_learn.service.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrmLearnApplicationTests {

	@Autowired
	private CountryService countryService;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldLoadSeededCountries() {
		assertThat(countryService.getAllCountries()).isNotEmpty();
	}

}
