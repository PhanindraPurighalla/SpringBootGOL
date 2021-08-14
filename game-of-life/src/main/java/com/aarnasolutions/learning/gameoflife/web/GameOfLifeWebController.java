package com.aarnasolutions.learning.gameoflife.web;

import com.aarnasolutions.learning.gameoflife.business.service.ActionService;
import com.aarnasolutions.learning.gameoflife.business.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        var actions = this.actionService.getActions();
        model.addAttribute("actions", actions);
        return "actions";
    }

    @GetMapping("/home")
    public String home() {
        return "landing";
    }

    @GetMapping("/games")
    public String getGames(Model model) {
        var games = this.gameService.getGames();
        model.addAttribute("games", games);
        return "games";
    }
}
