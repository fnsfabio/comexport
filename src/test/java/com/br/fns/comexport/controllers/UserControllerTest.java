package com.br.fns.comexport.controllers;

import com.br.fns.comexport.repositories.RoleRepository;
import com.br.fns.comexport.repositories.UserRepository;
import com.br.fns.comexport.security.ComexportSecurity;
import com.br.fns.comexport.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    UserController userController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void shouldNotNull(){
        assertThat(userController).isNotNull();
    }

    @Before
    public void setup(){
        SecurityContextHolder.clearContext();
        ComexportSecurity.executeAs("system", "system", "ROLE_USER", "ROLE_ADMIN");
        sdf.setLenient(false);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldBeSaveUserEmailAndBirthDateValidationException() throws Exception {

        UserVO userVO = new UserVO();
        userVO.setName("usuario sem email");
        String userString = objectMapper.writeValueAsString(userVO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/user")
                .content(userString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Is.is("Campo EMAIL deve ser preenchido")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate", Is.is("Campo DATA DE NASCIMENTO deve ser preenchido")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldBeSaveUserValidationOk() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setName("teste");
        userVO.setEmail("teste@teste.com");
        userVO.setBirthDate(sdf.parse("2000-01-01"));
        String userString = objectMapper.writeValueAsString(userVO);

        MediaType jsonType = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/user")
                .content(userString)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(jsonType));
    }

    @Test
    public void shouldBeSaveUserValidationEmailException() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setName("teste");
        userVO.setEmail("teste");
        userVO.setBirthDate(sdf.parse("2000-01-01"));
        String userString = objectMapper.writeValueAsString(userVO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .content(userString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Is.is("Campo EMAIL inválido")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldBeSaveUserValidationDateException() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setName("teste");
        userVO.setEmail("teste@teste.com");
        String userString = objectMapper.writeValueAsString(userVO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .content(userString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate", Is.is("Campo DATA DE NASCIMENTO deve ser preenchido")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
