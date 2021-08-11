package com.aarnasolutions.learning.gameoflife.data.repository;

import com.aarnasolutions.learning.gameoflife.data.entity.Action;
import org.springframework.data.repository.CrudRepository;

public interface ActionRepository extends CrudRepository<Action, Long> {
}
