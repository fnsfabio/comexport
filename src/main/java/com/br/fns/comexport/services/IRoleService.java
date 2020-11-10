package com.br.fns.comexport.services;

import com.br.fns.comexport.dto.RoleDTO;

import java.util.List;

public interface IRoleService {

    public List<RoleDTO> getRoles();

    public RoleDTO save(RoleDTO roleDTO);

    public RoleDTO getById(Long id);

    public void removeById(Long id);
}
