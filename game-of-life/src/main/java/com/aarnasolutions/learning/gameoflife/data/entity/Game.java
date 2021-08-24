package com.aarnasolutions.learning.gameoflife.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GAME")
public class Game {
    @Id
    @Column(name = "GAME_ID")
    private String gameId;

    /*@Column(name="PLAYER_ID")
    private long playerId;*/

    @Column(name = "NUM_PLAYERS")
    private int numPlayers;

    @ElementCollection
    @CollectionTable(name = "PLAYER", joinColumns = @JoinColumn(name = "PLAYER_ID"))
    private List<Player> players = new ArrayList<>();
}
