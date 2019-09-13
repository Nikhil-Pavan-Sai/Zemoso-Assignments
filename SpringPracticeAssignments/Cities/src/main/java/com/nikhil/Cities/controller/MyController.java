package com.nikhil.Cities.controller;

import com.nikhil.Cities.model.City;
import com.nikhil.Cities.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    private CityRepository repository;

    @PostMapping("/cities")
    public City addCity(@Valid @RequestBody City newCity)
    {

        return repository.save(newCity);

    }

    @GetMapping(value = "/cities")
    public List<City> getCities()
    {

        return repository.findAll();

    }
}