package com.br.fns.comexport.services;

import com.br.fns.comexport.dto.UserDTO;
import com.br.fns.comexport.entity.user.User;
import com.br.fns.comexport.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<UserDTO> getUsers() {
        return Arrays.asList(modelMapper.map(userRepository.findAll(), UserDTO[].class));
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User userSave = modelMapper.map(userDTO, User.class);
        userSave.setCreatedAt(new Date());
        userSave.setUpdatedAt(new Date());
        userSave.setEnabled(true);
        userRepository.save(userSave);
        return modelMapper.map(userSave, UserDTO.class);
    }

    @Override
    public UserDTO getById(Long id) {
        return userRepository.findById(id)
                .map(userFind -> modelMapper.map(userFind, UserDTO.class))
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void removeById(Long id) {
        userRepository.deleteById(id);
    }
}
