package com.example.login.domain.repository

import com.example.login.domain.model.User

interface UserRepository {
    fun find(email: String): User?
    fun findAll():List<User>?
}