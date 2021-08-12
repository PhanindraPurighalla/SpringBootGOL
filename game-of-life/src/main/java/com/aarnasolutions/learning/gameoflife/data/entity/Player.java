package com.aarnasolutions.learning.gameoflife.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="PLAYER")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PLAYER_ID")
    private long playerId;

    @Column(name="PLAYER_NAME")
    private String playerName;

    @Column(name="CAREER_ID")
    private long careerId;

    @Column(name="CASH")
    private long cash;

    @Column(name="BANK_LOAN")
    private long bankLoan;

    @Column(name="NUMBER_SPUN")
    private long numberSpun;

}
