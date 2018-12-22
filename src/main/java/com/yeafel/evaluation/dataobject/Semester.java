package com.yeafel.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long semesterId;

    /** 学年名称 */
    private String semesterName;

}
