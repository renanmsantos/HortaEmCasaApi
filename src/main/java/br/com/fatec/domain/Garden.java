package br.com.fatec.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn
    private User user;

    private Double userWidth;
    private Double userHeight;
    private Double userLength;



}
