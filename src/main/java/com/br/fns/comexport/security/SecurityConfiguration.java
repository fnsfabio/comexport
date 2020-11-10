package com.br.fns.comexport.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        UserDetails rose = userBuilder.username("rose").password("rose").roles("USER").build();
        UserDetails frank = userBuilder.username("frank").password("frank").roles("ADMIN").build();

        return new InMemoryUserDetailsManager(rose, frank);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/role").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/user").hasRole("ADMIN").and()
                .csrf().disable();
    }
}
