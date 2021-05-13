package com.example.login.domain.model

import com.example.login.domain.enum.RoleType

data class User(
    var id: Long,
    var email: String,
    var password: String,
    var name: String,
    var roleType: RoleType
)
