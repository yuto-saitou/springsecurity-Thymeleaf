package com.example.login.application.service

import com.example.login.domain.model.User
import com.example.login.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminUserService(
    private val userRepository: UserRepository
    ){
//    @Transactional
    fun register(user: User){
        userRepository.register(user)
    }

}