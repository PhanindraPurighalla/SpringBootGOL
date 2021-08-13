package com.aarnasolutions.learning.gameoflife.controller;

import com.aarnasolutions.learning.gameoflife.business.domain.Player;
import com.aarnasolutions.learning.gameoflife.business.service.GameService;
import com.aarnasolutions.learning.gameoflife.business.domain.Game;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game-of-life")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/getGames")
    public List<Game> getGames() {
        return this.gameService.getGames();
    }

    @PostMapping("/init")
    public com.aarnasolutions.learning.gameoflife.data.entity.Game initGame(@RequestBody Player player) {
        return gameService.initGame(player);
    }

    @PutMapping("/join")
    public void joinGame(@RequestParam String gameId, @RequestBody Player player) {
        gameService.joinGame(gameId, player);
    }
}

