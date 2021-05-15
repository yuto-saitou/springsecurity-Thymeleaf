package com.example.login.presentation.handler

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SpringSecurityAutheticationSuccessHandler : AuthenticationSuccessHandler{

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        response.status = HttpServletResponse.SC_OK
        val redirectStrategy = DefaultRedirectStrategy()
        val roles = AuthorityUtils.authorityListToSet(authentication.authorities)

        if (roles.contains("ADMIN")){
            redirectStrategy.sendRedirect(request,response,"/admin/top")
        }else if (roles.contains("USER")){
            redirectStrategy.sendRedirect(request,response,"/login/userlist")
        }
//        redirectStrategy.sendRedirect(request,response,"/login/userlist")
    }
}