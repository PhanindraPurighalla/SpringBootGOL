package com.aarnasolutions.learning.gameoflife.controller;

import com.aarnasolutions.learning.gameoflife.data.entity.Cell;
import com.aarnasolutions.learning.gameoflife.data.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cells")
public class CellController {
    @Autowired
    private CellRepository cellRepository;

    @GetMapping
    public Iterable<Cell> getCells() {
        return this.cellRepository.findAll();
    }
}

