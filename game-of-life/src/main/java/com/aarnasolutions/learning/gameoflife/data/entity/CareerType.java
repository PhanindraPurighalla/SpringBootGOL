package com.aarnasolutions.learning.gameoflife.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CAREER_TYPE")
public class CareerType {
    @Id
    @Column(name="CAREER_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long careerTypeId;

    @Column(name="CAREER_TYPE")
    private String careerType;

    @Column(name="CAREER_TYPE_DESCRIPTION")
    private String careerTypeDescription;
}
