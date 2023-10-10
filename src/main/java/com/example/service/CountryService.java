package com.example.service;

import com.example.entity.Car;
import com.example.entity.Country;
import com.example.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {


    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountry() {
        return  countryRepository.findAll();
    }

    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    public Country EditCountry(Long id, Country country) {
       Optional<Country> country1= countryRepository.findById(id);
        if (country1.isPresent()){
            Country  foundedCountry=country1.get();
            foundedCountry.setName(country.getName());
            countryRepository.save(foundedCountry);
            return foundedCountry;
        }return null;
    }

    public void deleteCountry(Long id) {
         countryRepository.deleteById(id);
    }

}
