package com.cognizant.orm_learn.service;

import com.cognizant.orm_learn.model.Stock;
import com.cognizant.orm_learn.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Transactional
    public List<Stock> getFacebookSeptemberStocks() {
        return stockRepository.findByCodeAndDateBetween(
                "FB",
                LocalDate.of(2019, 9, 1),
                LocalDate.of(2019, 9, 30)
        );
    }

    @Transactional
    public List<Stock> getGoogleStocksAbove1250() {
        return stockRepository.findByCodeAndCloseGreaterThan(
                "GOOGL",
                new BigDecimal("1250")
        );
    }

    @Transactional
    public List<Stock> getTop3HighestVolume() {
        return stockRepository.findTop3ByOrderByVolumeDesc();
    }

    @Transactional
    public List<Stock> getNetflixLowest3Close() {
        return stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
    }
}