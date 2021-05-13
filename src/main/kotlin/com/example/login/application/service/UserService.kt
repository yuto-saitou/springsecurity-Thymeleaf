package com.example.login.application.service

import com.example.login.domain.model.User
import com.example.login.domain.repository.UserRepository
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class UserService(
    private  val userRepository: UserRepository
) {
    fun getAllUser(): List<User>{
        return userRepository.findAll() ?: throw IllegalStateException("error")
    }
}