package br.com.fatec.domain;

import br.com.fatec.domain.enuns.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    private String userName;
    @NotNull
    private String userLogin;
    private String userPassword;
    private String userMail;

    private UserStatus userStatus;
    private Date userDateCreated;
    private Date userDateUpdated;

}
