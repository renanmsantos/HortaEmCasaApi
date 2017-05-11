package br.com.fatec.domain;

import br.com.fatec.domain.enuns.TypeStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class TypeGarden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeGardenId;

    private String typeName;
    private String typeDescription;
    private Double typeWidth;
    private Double typeHeight;
    private Double typeLength;
    @Lob
    private String typeHowToDo;
    @NotNull
    private TypeStatus typeStatus;

    @NotNull
    private Date typeDateCreated;
    private Date typeDateUpdated;


}
