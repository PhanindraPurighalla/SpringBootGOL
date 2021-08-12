package com.aarnasolutions.learning.gameoflife.business.service;

import com.aarnasolutions.learning.gameoflife.data.entity.Action;
import com.aarnasolutions.learning.gameoflife.data.repository.ActionRepository;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

    private final ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public Iterable<Action> getActions() {
        return actionRepository.findAll();
    }
}
