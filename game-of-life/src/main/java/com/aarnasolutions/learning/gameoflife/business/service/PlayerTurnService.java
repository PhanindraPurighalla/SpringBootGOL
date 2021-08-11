package com.aarnasolutions.learning.gameoflife.business.service;

import com.aarnasolutions.learning.gameoflife.business.domain.PlayerTurn;
import com.aarnasolutions.learning.gameoflife.data.entity.Cell;
import com.aarnasolutions.learning.gameoflife.data.repository.CellRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

@Service
public class PlayerTurnService {

    private final CellRepository cellRepository;

    public PlayerTurnService(CellRepository cellRepository) {
        this.cellRepository = cellRepository;
    }

    public Cell getNextCell(PlayerTurn playerTurn) {
        // We need to look-up the cellRepository to find the cells involved in this turn of the player
        // In the process, we also need to check of there are any decision points, in which case, we need to STOP
        // even though there are still moves left for the number spun

        // Find the cell on which the player was before the number was spun
        var previousCell = cellRepository.findById(playerTurn.getCurrentBoardPosition());

        // Move by the number spun until we reach a life choice
        var foundCell = previousCell.flatMap(cell -> cellRepository.findById(cell.getNextCellId() + playerTurn.getNumberSpun())).orElse(null);

        return foundCell;
    }
}
