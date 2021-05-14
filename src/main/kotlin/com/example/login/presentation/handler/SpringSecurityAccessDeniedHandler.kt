package com.example.login.presentation.handler

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SpringSecurityAccessDeniedHandler : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.status = HttpServletResponse.SC_FORBIDDEN
    }

}