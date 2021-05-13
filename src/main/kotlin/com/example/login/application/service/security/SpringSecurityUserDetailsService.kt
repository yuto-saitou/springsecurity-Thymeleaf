package com.example.login.application.service.security

import com.example.login.application.service.AuthenticationService
import com.example.login.domain.enum.RoleType
import com.example.login.domain.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class SpringSecurityUserDetailsService (
    private val authenticationService: AuthenticationService
    ) : UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails? {
        val user = authenticationService.findUser(username)
        return user?.let { SpringSecurityUserDetails(user) }
    }
}

data class SpringSecurityUserDetails(
    var id: Long,
    var email: String,
    var pass: String,
    var name: String,
    var roleType: RoleType
) : UserDetails{
    constructor(user: User) : this(user.id, user.email, user.password, user.name, user.roleType)

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList(this.roleType.toString())
    }

    override fun getPassword(): String {
        return this.pass
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}