package com.aarnasolutions.learning.gameoflife.business.domain;

import com.aarnasolutions.learning.gameoflife.data.entity.Career;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private String playerName;
    private Career career;
    private long cash;
    private long bankLoan;
    private int numberSpun;
    private int numPlayers;
    private long cellPosition;
}
