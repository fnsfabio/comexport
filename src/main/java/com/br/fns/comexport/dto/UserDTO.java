package com.br.fns.comexport.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 6284361839890473155L;

    private Long id;
    private String name;
    private String email;
    private Date birthDate;
    private Date createdAt;
    private Date updatedAt;
    private Boolean enabled;
    private Long idRole;
}
