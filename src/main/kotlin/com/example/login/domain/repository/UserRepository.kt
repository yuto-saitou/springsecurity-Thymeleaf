package com.example.login.domain.repository

import com.example.login.domain.model.User

interface UserRepository {
    fun find(email: String): User?//AuthenticationServiceで使う (実装はUserRepositoryImplに記載)
    fun findAll():List<User>?//UserServiceで使う全件検索の処理 (実装はUserRepositoryImplに記載)
    fun register(user: User)//AdminUserServiceで使う
}