package com.aarnasolutions.learning.gameoflife.business.domain;

import com.aarnasolutions.learning.gameoflife.data.entity.Career;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerTurn {
    private long playerId;
    private long currentBoardPosition;
    private long numberSpun;
    private Career career;
}
