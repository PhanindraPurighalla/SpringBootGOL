package com.aarnasolutions.learning.gameoflife.web;

import com.aarnasolutions.learning.gameoflife.business.domain.PlayerTurn;
import com.aarnasolutions.learning.gameoflife.business.service.PlayerTurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/player-turns")
public class PlayerTurnWebController {

    private final PlayerTurnService playerTurnService;

    @Autowired
    public PlayerTurnWebController(PlayerTurnService playerTurnService) {
        this.playerTurnService = playerTurnService;
    }

    @GetMapping
    public String getNextCell(@RequestBody PlayerTurn playerTurn, Model model) {
        var traversedCells = this.playerTurnService.getTraversedCells(playerTurn);
        model.addAttribute("traversedCells", traversedCells);
        return "/playerTurnDetails";
    }
}
