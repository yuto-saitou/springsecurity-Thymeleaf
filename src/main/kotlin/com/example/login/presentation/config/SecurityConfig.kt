package com.example.login.presentation.config

import com.example.login.domain.enum.RoleType
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity

@EnableWebSecurity
class SecurityConfig() : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .mvcMatchers("/login").permitAll()
            .mvcMatchers("/login/userlist").permitAll()
            .mvcMatchers("admin/**").hasAuthority(RoleType.ADMIN.toString())
            .anyRequest().authenticated()
            .and()

            .csrf().disable()
            .formLogin()

            .loginProcessingUrl("/login/auth")

            .usernameParameter("email")
            .passwordParameter("pass")
            .successHandler(SpringSecurityAuthenticationSuccessHandler())
            .failureHandler(SpringSecurityAuthenticationFailureHandler())
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(SpringSecurityAuthenticationEntryPoint())
            .accessDeniedHandler(SpringSecurityAccessDeniedHandler())

    }

    override fun configure (auth: AuthenticationManagerBuilder){

    }

}