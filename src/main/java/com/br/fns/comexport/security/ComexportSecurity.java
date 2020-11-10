package com.br.fns.comexport.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

public class ComexportSecurity {

    public static void executeAs(String userName, String password, String... roles) {
        Assert.notNull(userName, "Usuário não pode ser nulo");
        Assert.notNull(password, "Senha não pode ser nulo");

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userName, password, AuthorityUtils.createAuthorityList(roles)));
    }
}
