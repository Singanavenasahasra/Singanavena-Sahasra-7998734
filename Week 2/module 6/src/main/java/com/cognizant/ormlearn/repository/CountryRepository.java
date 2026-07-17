package com.cognizant.ormlearn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cognizant.ormlearn.model.Country;

public interface CountryRepository extends JpaRepository<Country, String> {
    
    List<Country> findByNameContaining(String name);
    
    List<Country> findByNameContainingOrderByNameAsc(String name);
    
    List<Country> findByNameStartingWith(String alphabet);
}