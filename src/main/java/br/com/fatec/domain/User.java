package br.com.fatec.domain;

import br.com.fatec.domain.enuns.UserStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
    @NotNull
    private String userPassword;
    @NotNull
    private String userMail;
    @NotNull
    private UserStatus userStatus;

    private Double userWidth;
    private Double userHeight;
    private Double userLength;

    @NotNull
    private Date userDateCreated;
    private Date userDateUpdated;

}
