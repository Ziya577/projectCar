package com.example.controller;

import com.example.entity.Country;
import com.example.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
@GetMapping("/get")
    public List<Country> getAllCountry(){
        return countryService.getAllCountry();
    }

    @PostMapping("/create")
    public Country createCountry(@RequestBody Country country){
        return countryService.createCountry(country);
    }
    @PutMapping("/edit/{id}")
    public Country editCountry(@PathVariable Long id,@RequestBody Country country){
        return countryService.EditCountry(id,country);

    }
    @DeleteMapping("/delete/{id}")
    public void deleteCountry(@PathVariable Long id){
         countryService.deleteCountry(id);
    }
}
