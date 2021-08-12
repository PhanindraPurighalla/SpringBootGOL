package com.aarnasolutions.learning.gameoflife.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="GAME")
public class Game {
    @Id
    @Column(name="GAME_ID")
    private String gameId;

    @Column(name="PLAYER_ID")
    private long playerId;

}
