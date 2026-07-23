package com.cognizant.orm_learn.repository;

import com.cognizant.orm_learn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Existing method
    List<Country> findByNameContainingIgnoreCase(String name);

    // 1. Search by text
    List<Country> findByNameContaining(String text);

    // 2. Search by text + ascending order
    List<Country> findByNameContainingOrderByNameAsc(String text);

    // 3. Search countries starting with letter
    List<Country> findByNameStartingWith(String alphabet);
}