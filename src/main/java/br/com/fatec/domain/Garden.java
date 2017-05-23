package br.com.fatec.domain;

import br.com.fatec.domain.enuns.GardenStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenId;

    @NotNull
    private Double gardenWidth;
    @NotNull
    private Double gardenHeight;
    @NotNull
    private Double gardenLength;

    private GardenStatus gardenStatus;

    private Date gardenDateCreated;
    private Date gardenDateUpdated;

    @JsonIgnore
    @OneToMany(mappedBy = "garden")
    private List<User>  users;

    @ManyToMany
    @JoinTable(name = "listTypeGarden",
            joinColumns={@JoinColumn(name="gardenId")},
            inverseJoinColumns= {@JoinColumn(name="typeId")})
    private List<Type> types;


}
