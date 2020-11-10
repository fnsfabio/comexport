package com.br.fns.comexport.services;

import com.br.fns.comexport.dto.UserDTO;

import java.util.List;

public interface IUserService {

    public List<UserDTO> getUsers();

    public UserDTO save(UserDTO userDTO);

    public UserDTO getById(Long id);

    public void removeById(Long id);
}
