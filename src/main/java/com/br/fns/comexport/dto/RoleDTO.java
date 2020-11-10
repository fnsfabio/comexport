package com.br.fns.comexport.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 2962870902628997877L;

    private Long id;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Boolean enabled;
}
