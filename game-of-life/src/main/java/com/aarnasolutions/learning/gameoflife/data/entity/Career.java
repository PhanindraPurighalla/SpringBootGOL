package com.aarnasolutions.learning.gameoflife.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CAREER")
public class Career {
    @Id
    @Column(name="CAREER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long careerId;

    @Column(name="CAREER")
    private String career;

    @Column(name="CAREER_DESCRIPTION")
    private String careerDescription;

    @Column(name="CAREER_TYPE_ID")
    private long careerTypeId;

    @Column(name="CAREER_SALARY")
    private long careerSalary;

    @Column(name="CAREER_MAX_SALARY")
    private long careerMaxSalary;

    @Column(name="CAREER_TAXES_DUE")
    private long careerTaxesDue;
}
