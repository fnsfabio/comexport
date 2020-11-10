package com.br.fns.comexport.controllers;

import com.br.fns.comexport.dto.RoleDTO;
import com.br.fns.comexport.services.IRoleService;
import com.br.fns.comexport.vo.RoleVO;
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
@RequestMapping("/role")
public class RoleController {

    private final IRoleService roleService;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public RoleController(IRoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    @ApiOperation(value = "Obtem a lista de ROLES cadastrados", response = String.class)
    @ApiResponses( value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Lista de ROLES disponível", response = RoleVO[].class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Erro durante a consulta", response = String.class)
    })
    public List<RoleVO> getRoles(){
        return Arrays.asList(modelMapper.map(roleService.getRoles(), RoleVO[].class));
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Persiste nova role", response = ResponseEntity.class)
    @ApiResponses( value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "ROLE salva", response = ResponseEntity.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Erro durante ao verficar os campos", response = Map.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Erro durante a persistência", response = String.class)
    })
    public ResponseEntity<RoleVO> saveRole(@Valid @RequestBody RoleVO roleVO){
        try{
            RoleDTO RoleDTO = modelMapper.map(roleVO, RoleDTO.class);
            RoleVO roleVOSalva = modelMapper.map(roleService.save(RoleDTO), RoleVO.class);
            return ResponseEntity.ok(roleVOSalva);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
