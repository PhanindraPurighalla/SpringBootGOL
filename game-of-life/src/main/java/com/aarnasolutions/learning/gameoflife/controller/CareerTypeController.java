package com.aarnasolutions.learning.gameoflife.controller;

import com.aarnasolutions.learning.gameoflife.data.entity.CareerType;
import com.aarnasolutions.learning.gameoflife.data.repository.CareerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/career-types")
public class CareerTypeController {
    @Autowired
    private CareerTypeRepository careerTypeRepository;

    @GetMapping
    public Iterable<CareerType> getCareerTypes() {
        return this.careerTypeRepository.findAll();
    }
}

