package com.example.login.presentation.config

import com.example.login.application.service.AuthenticationService
import com.example.login.application.service.security.SpringSecurityUserDetailsService
import com.example.login.domain.enum.RoleType
import com.example.login.presentation.handler.SpringSecurityAccessDeniedHandler
import com.example.login.presentation.handler.SpringSecurityAuthenticationEntryPoint
import com.example.login.presentation.handler.SpringSecurityAuthenticationFailureHandler
import com.example.login.presentation.handler.SpringSecurityAutheticationSuccessHandler
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@EnableWebSecurity
class SecurityConfig(private val authenticationService: AuthenticationService) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .mvcMatchers("/login","error/**").permitAll()
//            .mvcMatchers("/login/userlist").permitAll()
            .mvcMatchers("admin/**").hasAuthority(RoleType.ADMIN.toString())
            .anyRequest().authenticated()

            .and()
            .csrf().disable()
            .formLogin()
            .loginProcessingUrl("/login/auth")
            .usernameParameter("email")
            .passwordParameter("pass")
            .successHandler(SpringSecurityAutheticationSuccessHandler())
            .failureHandler(SpringSecurityAuthenticationFailureHandler())

            .and()
            .exceptionHandling()
            .authenticationEntryPoint(SpringSecurityAuthenticationEntryPoint())
            .accessDeniedHandler(SpringSecurityAccessDeniedHandler())

            .and().logout()
            .logoutUrl("/user/logout")
            .logoutSuccessUrl("/login")

            .and()
            .sessionManagement()
            .invalidSessionUrl("/error")

    }

    override fun configure (auth: AuthenticationManagerBuilder){
        auth.userDetailsService(SpringSecurityUserDetailsService(authenticationService))//認証処理を行うクラスを指定
            .passwordEncoder(BCryptPasswordEncoder())//パスワードの暗号化アルゴリズムを指定(今回はBCrypt)

    }

}