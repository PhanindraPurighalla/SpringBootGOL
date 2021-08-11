package com.aarnasolutions.learning.gameoflife.business.service;

import com.aarnasolutions.learning.gameoflife.business.domain.PlayerTurn;
import com.aarnasolutions.learning.gameoflife.business.domain.TraversedCell;
import com.aarnasolutions.learning.gameoflife.data.entity.Cell;
import com.aarnasolutions.learning.gameoflife.data.repository.ActionRepository;
import com.aarnasolutions.learning.gameoflife.data.repository.CellRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerTurnService {

    private final CellRepository cellRepository;
    private final ActionRepository actionRepository;

    public PlayerTurnService(CellRepository cellRepository, ActionRepository actionRepository) {
        this.cellRepository = cellRepository;
        this.actionRepository = actionRepository;
    }

    public List<TraversedCell> getTraversedCells(PlayerTurn playerTurn) {
        // We need to look-up the cellRepository to find the cells involved in this turn of the player
        // In the process, we also need to check if there are any decision points, in which case, we need to STOP
        // even though there are still moves left for the number spun

        // Find the cell on which the player was before the number was spun
        var previousCell = cellRepository.findById(playerTurn.getCurrentBoardPosition());

        List<TraversedCell> traversedCells = new ArrayList<>();

        // Move "numberSpun" times or until we reach a life choice
        var movesRemaining = true;
        var traversedCellCount = 0;
        while (movesRemaining) {
            var nextCell = previousCell.filter(cell -> cell.getNextCellId() != null).flatMap(prevCell -> cellRepository.findById(prevCell.getNextCellId()));
            if (nextCell.isPresent()) {
                var action = actionRepository.findById(nextCell.get().getCellActionId()).orElse(null);
                if (action != null) {
                    var traversedCell = TraversedCell.builder()
                            .cellId(nextCell.get().getCellId())
                            .cellActionId(nextCell.get().getCellActionId())
                            .cellActionDesc(action.getActionName())
                            .build();
                    traversedCells.add(traversedCell);
                    traversedCellCount++;
                    previousCell = nextCell;
                    if (traversedCellCount == playerTurn.getNumberSpun() || action.getActionIsLifeChoice() == 'Y') {
                        movesRemaining = false;
                    }
                } else {
                    movesRemaining = false;
                }
            }
            else {
                movesRemaining = false;
            }
        }
        return traversedCells;
    }
}
