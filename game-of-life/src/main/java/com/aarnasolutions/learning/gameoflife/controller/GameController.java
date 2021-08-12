package com.aarnasolutions.learning.gameoflife.controller;

import com.aarnasolutions.learning.gameoflife.business.domain.Player;
import com.aarnasolutions.learning.gameoflife.business.service.GameService;
import com.aarnasolutions.learning.gameoflife.business.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getGames() {
        return this.gameService.getStartedGames();
    }

    @PostMapping
    public void startGame(@RequestBody Player player) {
        gameService.startGame(player);
    }
}

