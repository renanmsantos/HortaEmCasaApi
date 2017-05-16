package br.com.fatec.domain;

import br.com.fatec.domain.enuns.GardenStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenId;

    private Double gardenWidth;
    private Double gardenHeight;
    private Double gardenLength;

    @NotNull
    private GardenStatus gardenStatus;
    @NotNull
    private Date gardenDateCreated;
    private Date gardenDateUpdated;

}
