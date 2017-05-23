package br.com.fatec.domain;

import br.com.fatec.domain.enuns.TypeStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    private String typeName;
    private String typeDescription;
    private Double typeWidth;
    private Double typeHeight;
    private Double typeLength;
    @Column(columnDefinition="TEXT")
    private String typeHowToDo;

    private TypeStatus typeStatus;
    private Date typeDateCreated;
    private Date typeDateUpdated;

    @ManyToMany
    @JoinTable(name = "listPlant",
            joinColumns={@JoinColumn(name="typeId")},
            inverseJoinColumns= {@JoinColumn(name="PlantId")})
    private List<Plant> plants;


}
