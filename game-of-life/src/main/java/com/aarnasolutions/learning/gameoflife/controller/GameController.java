package com.aarnasolutions.learning.gameoflife.controller;

import com.aarnasolutions.learning.gameoflife.business.service.GameService;
import com.aarnasolutions.learning.gameoflife.business.service.PlayerTurnService;
import com.aarnasolutions.learning.gameoflife.data.entity.Game;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game-of-life")
public class GameController {

  private final GameService gameService;
  private final PlayerTurnService playerTurnService;

  public GameController(GameService gameService, PlayerTurnService playerTurnService) {
    this.gameService = gameService;
    this.playerTurnService = playerTurnService;
  }

  @GetMapping("/getGames")
  public Iterable<Game> getGames() {
    return this.gameService.getGames();
  }

  @PostMapping("/init")
  public com.aarnasolutions.learning.gameoflife.data.entity.Game initGame(
      @RequestParam String playerName, @RequestParam int numPlayers) {
    return gameService.initGame(playerName, numPlayers);
  }

  @PutMapping("/join")
  public void joinGame(@RequestParam String gameId, @RequestParam String playerName) {
    gameService.joinGame(gameId, playerName);
  }

  @GetMapping("/get-next-cellId")
  public long getNextCellId(@RequestParam long currentCell, @RequestParam int numberSpun) {
    return playerTurnService.getNextCellId(currentCell, numberSpun);
  }

  @PutMapping("/move-player-peg")
  public com.aarnasolutions.learning.gameoflife.data.entity.Game movePlayerPeg(
      @RequestParam String gameId, @RequestParam int numberSpun, @RequestParam String playerName) {
    return gameService.movePlayerPeg(gameId, playerName, numberSpun).orElse(null);
  }
}
