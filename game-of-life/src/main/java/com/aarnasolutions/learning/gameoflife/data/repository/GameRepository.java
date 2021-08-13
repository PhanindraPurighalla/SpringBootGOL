package com.aarnasolutions.learning.gameoflife.data.repository;

import com.aarnasolutions.learning.gameoflife.data.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, String> {
}
