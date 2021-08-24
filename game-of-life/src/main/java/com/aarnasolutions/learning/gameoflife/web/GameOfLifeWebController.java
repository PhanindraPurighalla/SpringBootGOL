package com.aarnasolutions.learning.gameoflife.web;

import com.aarnasolutions.learning.gameoflife.business.service.ActionService;
import com.aarnasolutions.learning.gameoflife.business.service.CellService;
import com.aarnasolutions.learning.gameoflife.business.service.GameService;
import com.aarnasolutions.learning.gameoflife.data.entity.Action;
import com.aarnasolutions.learning.gameoflife.data.entity.Cell;
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
    private final CellService cellService;

    @Autowired
    public GameOfLifeWebController(
            ActionService actionService, GameService gameService, CellService cellService) {
        this.actionService = actionService;
        this.gameService = gameService;
        this.cellService = cellService;
    }

    @GetMapping("/actions")
    public String getActions(Model model) {
        Iterable<Action> actions = this.actionService.getActions();
        model.addAttribute("actions", actions);
        return "actions";
    }

    @GetMapping("/cells")
    public String getCells(Model model) {
        Iterable<Cell> cells = this.cellService.getCells();
        model.addAttribute("cells", cells);
        return "cells";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("player", new Player());
        return "landing";
    }

    @GetMapping("/games")
    public String getGames(Model model) {
        var domainGames = this.gameService.getDomainGames();
        model.addAttribute("games", domainGames);
        return "games";
    }

    @GetMapping("/join-game")
    public String getGamesToJoin(Model model, @ModelAttribute("player") Player player) {
        var domainGames = this.gameService.getDomainGames();
        domainGames.forEach(
                game ->
                        game.setJoinPermissible(
                                game.getPlayers().size() < game.getNumPlayers()
                                        && game.getPlayers().stream()
                                                .noneMatch(
                                                        player1 ->
                                                                player.getPlayerName()
                                                                        .equals(
                                                                                player1
                                                                                        .getPlayerName()))));
        model.addAttribute("games", domainGames);
        model.addAttribute("joiningPlayer", player.getPlayerName());
        return "games";
    }

    @PostMapping("/create")
    public String initGame(
            @RequestParam String playerName, @RequestParam int numPlayers, Model model) {
        var initiatedGame = gameService.initGame(playerName, numPlayers);
        var domainGames = this.gameService.getDomainGames();
        domainGames.forEach(
                game ->
                        game.setJoinPermissible(
                                game.getPlayers().size() < game.getNumPlayers()
                                        && game.getPlayers().stream()
                                                .noneMatch(
                                                        player ->
                                                                playerName.equals(
                                                                        player.getPlayerName()))));
        model.addAttribute("games", domainGames);
        model.addAttribute("initiatingPlayer", playerName);
        model.addAttribute("initiatedGame", initiatedGame);
        model.addAttribute("playerName", playerName);
        model.addAttribute("joiningPlayer", playerName);
        model.addAttribute("player", new Player());

        return "games";
    }

    @PostMapping("/join")
    public String joinGame(
            @RequestParam("gameId") String gameId,
            @RequestParam("playerName") String playerName,
            Model model) {
        gameService.joinGame(gameId, playerName);
        var playersInGame = gameService.getPlayersByGameId(gameId);
        model.addAttribute("playersInGame", playersInGame);
        return "players";
    }

    @PostMapping("/move-player")
    public String movePlayer(
            @RequestParam String gameId,
            @RequestParam int numberSpun,
            @RequestParam String playerName,
            Model model) {
        gameService.movePlayerPeg(gameId, playerName, numberSpun).orElse(null);
        var domainGames = this.gameService.getDomainGames();
        domainGames.forEach(
                game ->
                        game.setJoinPermissible(
                                game.getPlayers().size() < game.getNumPlayers()
                                        && game.getPlayers().stream()
                                                .noneMatch(
                                                        player ->
                                                                playerName.equals(
                                                                        player.getPlayerName()))));
        model.addAttribute("games", domainGames);
        model.addAttribute("player", new Player());
        return "games";
    }
}
