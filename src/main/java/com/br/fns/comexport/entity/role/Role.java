package com.br.fns.comexport.entity.role;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Role", schema = "comexportdb")
public class Role implements Serializable {

    private static final long serialVersionUID = 6204107259691564446L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Column(name = "description")
    private String description;

	@Column(name = "createdAt")
    private Date createdAt;

	@Column(name = "updatedAt")
    private Date updatedAt;

	@Column(name = "enabled")
    private Boolean enabled;
}
