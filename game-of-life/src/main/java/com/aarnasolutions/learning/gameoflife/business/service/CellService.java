package com.aarnasolutions.learning.gameoflife.business.service;

import com.aarnasolutions.learning.gameoflife.data.entity.Action;
import com.aarnasolutions.learning.gameoflife.data.entity.Cell;
import com.aarnasolutions.learning.gameoflife.data.repository.ActionRepository;
import com.aarnasolutions.learning.gameoflife.data.repository.CellRepository;
import org.springframework.stereotype.Service;

@Service
public class CellService {

    private final CellRepository cellRepository;

    public CellService(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

    public Iterable<Cell> getCells() {
        return cellRepository.findAll();
    }
}
