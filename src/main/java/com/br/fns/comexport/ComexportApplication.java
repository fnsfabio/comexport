package com.br.fns.comexport;

import com.br.fns.comexport.security.ComexportSecurity;
import com.br.fns.comexport.services.IRoleService;
import com.br.fns.comexport.services.IUserService;
import com.br.fns.comexport.services.RoleService;
import com.br.fns.comexport.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ComexportApplication {
	public static void main(String[] args) {
		SpringApplication.run(ComexportApplication.class, args);
	}

	public @PostConstruct void setupApp(){
		ComexportSecurity.executeAs("system", "system", "ROLE_ADMIN");

		SecurityContextHolder.clearContext();
	}

	@Bean public IRoleService getRoleService() { return new RoleService(); }
	@Bean public IUserService getUserService() { return new UserService(); }
}
