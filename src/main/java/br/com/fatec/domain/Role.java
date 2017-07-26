package br.com.fatec.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    private String roleDescription;

    @ManyToMany
    @JoinTable(name = "listRoles",
            joinColumns={@JoinColumn(name="roleId")},
            inverseJoinColumns= {@JoinColumn(name="userId")})
    private List<Role> roles;
}
