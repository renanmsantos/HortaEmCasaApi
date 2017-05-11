package br.com.fatec.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plantId;

    @NotNull
    private String plantName;
    private String plantDescription;
    @Lob
    private String plantHowToPlant;
    @NotNull
    private Integer plantWeekPeriodicityWater;
    @NotNull
    private Integer plantWeekPeriodicityCare;

    private String plantPathImage;

}
