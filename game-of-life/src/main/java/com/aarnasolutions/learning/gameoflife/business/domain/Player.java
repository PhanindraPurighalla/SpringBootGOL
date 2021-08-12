package com.aarnasolutions.learning.gameoflife.business.domain;

import com.aarnasolutions.learning.gameoflife.data.entity.Career;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private long playerId;
    private String playerName;
    private Career career;
    private long cash;
    private long bankLoan;
    private long numberSpun;
}