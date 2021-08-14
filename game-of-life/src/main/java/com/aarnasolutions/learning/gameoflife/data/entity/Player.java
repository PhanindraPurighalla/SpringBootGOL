package com.aarnasolutions.learning.gameoflife.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(name = "PLAYER")
public class Player {
  @Column(name = "PLAYER_NAME")
  private String playerName;

  @Column(name = "CELL_POSITION")
  private long cellPosition;

  @Column(name = "CAREER_ID")
  private long careerId;

  @Column(name = "CASH")
  private long cash;

  @Column(name = "BANK_LOAN")
  private long bankLoan;

  @Column(name = "NUMBER_SPUN")
  private int numberSpun;
}
