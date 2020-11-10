package com.br.fns.comexport.services;

import com.br.fns.comexport.dto.RoleDTO;
import com.br.fns.comexport.entity.role.Role;
import com.br.fns.comexport.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<RoleDTO> getRoles() {
        return Arrays.asList(modelMapper.map(roleRepository.findAll(), RoleDTO[].class));
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role roleSave = modelMapper.map(roleDTO, Role.class);
        roleSave.setEnabled(true);
        roleSave.setCreatedAt(new Date());
        roleSave.setUpdatedAt(new Date());
        roleRepository.save(roleSave);
        return modelMapper.map(roleSave, RoleDTO.class);
    }

    @Override
    public RoleDTO getById(Long id) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }
}
