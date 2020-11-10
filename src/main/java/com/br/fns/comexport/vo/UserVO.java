package com.br.fns.comexport.vo;

import com.br.fns.comexport.validation.NotDateNull;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 5003866928323992243L;

    private Long id;

    private static final String DATE_FORMAT_PARSER = "yyyy-MM-dd";

    @NotBlank(message = "Campo NOME deve ser preenchido")
    private String name;

    @Email(message = "Campo EMAIL inválido")
    @NotBlank(message = "Campo EMAIL deve ser preenchido")
    private String email;

    @NotDateNull(message = "Campo DATA DE NASCIMENTO deve ser preenchido")
    private Date birthDate;

    private Boolean enabled;
}
