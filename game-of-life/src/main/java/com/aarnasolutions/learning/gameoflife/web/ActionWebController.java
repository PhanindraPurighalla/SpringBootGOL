package com.aarnasolutions.learning.gameoflife.web;

import com.aarnasolutions.learning.gameoflife.business.domain.PlayerTurn;
import com.aarnasolutions.learning.gameoflife.business.service.ActionService;
import com.aarnasolutions.learning.gameoflife.business.service.PlayerTurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gol-actions")
public class ActionWebController {

    private final ActionService actionService;

    @Autowired
    public ActionWebController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping
    public String getActions(Model model) {
        var actions = this.actionService.getActions();
        model.addAttribute("actions", actions);
        return "actions";
    }
}
