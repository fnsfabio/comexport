package com.br.fns.comexport.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class RoleVO implements Serializable {

    private static final long serialVersionUID = -8421262717613970898L;

    private Long id;

    @NotBlank(message = "Campo DESCRIPTION deve ser preenchido")
    private String description;

    private Boolean enabled;
}
