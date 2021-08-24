package com.aarnasolutions.learning.gameoflife.web;

import com.aarnasolutions.learning.gameoflife.business.service.ActionService;
import com.aarnasolutions.learning.gameoflife.business.service.GameService;
import com.aarnasolutions.learning.gameoflife.data.entity.Action;
import com.aarnasolutions.learning.gameoflife.data.entity.Game;
import com.aarnasolutions.learning.gameoflife.data.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/game-of-life")
public class GameOfLifeWebController {

    private final ActionService actionService;
    private final GameService gameService;

    @Autowired
    public GameOfLifeWebController(ActionService actionService, GameService gameService) {
        this.actionService = actionService;
        this.gameService = gameService;
    }

    @GetMapping("/actions")
    public String getActions(Model model) {
        Iterable<Action> actions = this.actionService.getActions();
        model.addAttribute("actions", actions);
        return "actions";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("player", new Player());
        return "landing";
    }

    @GetMapping("/games")
    public String getGames(Model model) {
        Iterable<Game> games = this.gameService.getGames();
        model.addAttribute("games", games);
        return "games";
    }

    @GetMapping("/join-game")
    public String getGamesToJoin(Model model, @ModelAttribute("player") Player player) {
        Iterable<Game> games = this.gameService.getGames();
        model.addAttribute("games", games);
        model.addAttribute("joiningPlayer", player.getPlayerName());
        return "games";
    }

    @PostMapping("/create")
    public String initGame(
            @RequestParam String playerName, @RequestParam int numPlayers, Model model) {
        var initiatedGame = gameService.initGame(playerName, numPlayers);
        model.addAttribute("initiatingPlayer", playerName);
        model.addAttribute("initiatedGame", initiatedGame);
        model.addAttribute("playerName", playerName);
        model.addAttribute("joiningPlayer", playerName);
        return "games";
    }

    @PostMapping("/join")
    public String joinGame(@RequestParam("gameId") String gameId, @RequestParam("playerName") String playerName, Model model) {
        gameService.joinGame(gameId, playerName);
        var playersInGame = gameService.getPlayersByGameId(gameId);
        model.addAttribute("playersInGame", playersInGame);
        return "players";
    }
}
