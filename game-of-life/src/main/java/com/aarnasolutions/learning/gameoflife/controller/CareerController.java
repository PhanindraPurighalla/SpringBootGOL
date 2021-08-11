package com.aarnasolutions.learning.gameoflife.controller;

import com.aarnasolutions.learning.gameoflife.data.entity.Career;
import com.aarnasolutions.learning.gameoflife.data.repository.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/careers")
public class CareerController {
    @Autowired
    private CareerRepository careerRepository;

    @GetMapping
    public Iterable<Career> getCareers() {
        return this.careerRepository.findAll();
    }
}

