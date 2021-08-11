package com.aarnasolutions.learning.gameoflife.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACTION")
public class Action {
    @Id
    @Column(name = "ACTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long actionId;

    @Column(name = "ACTION_NAME")
    private String actionName;

    @Column(name="IS_LIFE_CHOICE")
    private char actionIsLifeChoice;
}
