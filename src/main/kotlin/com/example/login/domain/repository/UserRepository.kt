package com.example.login.domain.repository

import com.example.login.domain.model.User

interface UserRepository {
    fun findAll():List<User>?//UserServiceで使う全件検索の処理(実装はUserRepositoryImplに記載
    fun find(email: String): User?//

}