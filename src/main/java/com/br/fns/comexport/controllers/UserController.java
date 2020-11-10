package com.br.fns.comexport.controllers;

import com.br.fns.comexport.dto.UserDTO;
import com.br.fns.comexport.services.IUserService;
import com.br.fns.comexport.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "API para controle dos usuários")
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "Obtem a lista de usuarios cadastrados", response = String.class)
    @ApiResponses( value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Lista de usuários disponível", response = UserVO[].class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Erro durante a consulta", response = String.class)
    })
    public List<UserVO> getUsers(){
        return Arrays.asList(modelMapper.map(userService.getUsers(), UserVO[].class));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtem usuario cadastrado pelo ID", response = ResponseEntity.class)
    @ApiResponses( value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Usuário cadastrado", response = UserVO.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Usuário NÃO encontrado", response = ResponseEntity.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Erro durante a consulta", response = String.class)
    })
    public ResponseEntity<UserVO> getUserById(@RequestParam(value = "id") Long id){
        return ResponseEntity.ok(modelMapper.map(userService.getById(id), UserVO.class));
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Persiste novo usuário", response = ResponseEntity.class)
    @ApiResponses( value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Usuário salvo", response = ResponseEntity.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Erro durante ao verficar os campos", response = Map.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Erro durante a persistência", response = String.class)
    })
    public ResponseEntity<UserVO> saveUser(@Valid @RequestBody UserVO userVO){
        try{
            UserDTO userDTO = modelMapper.map(userVO, UserDTO.class);
            UserVO userVO1 = modelMapper.map(userService.save(userDTO), UserVO.class);
            return ResponseEntity.ok(userVO1);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    @ApiOperation(value = "Atualiza dados do usuário", response = ResponseEntity.class)
    @ApiResponses( value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Usuário atualizado", response = ResponseEntity.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Erro durante ao verficar os campos", response = Map.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Erro durante a persistência", response = String.class)
    })
    public ResponseEntity<UserVO> updateUser(@Valid @RequestBody UserVO userVO){
        return ResponseEntity.ok(
                modelMapper.map(userService.save(
                        modelMapper.map(userVO, UserDTO.class)), UserVO.class));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove usuário", response = ResponseEntity.class)
    @ApiResponses( value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Usuário removido", response = ResponseEntity.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Erro durante a remoção", response = String.class)
    })
    public ResponseEntity<String> updateUser(@RequestParam(value = "id") Long id){
        userService.removeById(id);
        return ResponseEntity.ok(String.format("Usuário removido: %d", id));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((err) -> {
            String field = ((FieldError) err).getField();
            String messageError = err.getDefaultMessage();
            errors.put(field, messageError);
        });
        return errors;
    }
}
