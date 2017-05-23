package br.com.fatec.domain;

import br.com.fatec.domain.enuns.PlantStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plantId;

    @NotNull
    private String plantName;
    private String plantDescription;
    @Column(columnDefinition="TEXT")
    private String plantHowToPlant;
    @NotNull
    private Integer plantWeekPeriodicityWater;
    @NotNull
    private Integer plantWeekPeriodicityCare;
    private String plantPathImage;

    private PlantStatus plantStatus;

    private Date plantDateCreated;
    private Date plantDateUpdated;


}
