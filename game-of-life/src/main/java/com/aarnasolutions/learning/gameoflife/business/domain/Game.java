package com.aarnasolutions.learning.gameoflife.business.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Game {
    private String gameId;
    private List<Player> players;
    private int numPlayers;
    private boolean joinPermissible;
}
