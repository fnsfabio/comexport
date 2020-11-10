package com.br.fns.comexport.entity.user;

import com.br.fns.comexport.validation.NotDateNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "User", schema = "comexportdb")
public class User implements Serializable {

    private static final long serialVersionUID = 4750710730688428659L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Campo NOME deve ser preenchido")
	private String name;

    @Column(name = "email")
    @Email
    @NotBlank(message = "Campo EMAIL deve ser preenchido")
	private String email;

    @Column(name = "birthDate")
    @NotDateNull(message = "Campo DATA DE NASCIMENTO deve ser preenchido")
	private Date birthDate;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "idRole")
    private Long idRole;
}
