package com.aarnasolutions.learning.gameoflife.data.repository;

import com.aarnasolutions.learning.gameoflife.data.entity.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
