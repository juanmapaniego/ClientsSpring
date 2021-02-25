package com.jmpaniego.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    private final PasswordEncoder encoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("user")
                    .password(encoder.encode("user"))
                    .roles("USER")
                    .and()
                .withUser("admin")
                    .password(encoder.encode("admin"))
                    .roles("ADMIN","USER")
                ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/clients")
                .permitAll()
                .antMatchers("/clients/edit/**","/clients/delete/**")
                  .hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                    .exceptionHandling().accessDeniedPage("/errores/403")

               ;
    }
}
