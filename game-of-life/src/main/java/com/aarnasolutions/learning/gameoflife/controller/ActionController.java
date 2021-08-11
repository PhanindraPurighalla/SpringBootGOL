package com.aarnasolutions.learning.gameoflife.controller;

import com.aarnasolutions.learning.gameoflife.data.entity.Action;
import com.aarnasolutions.learning.gameoflife.data.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actions")
public class ActionController {
    @Autowired
    private ActionRepository actionRepository;

    @GetMapping
    public Iterable<Action> getActions() {
        return this.actionRepository.findAll();
    }
}

