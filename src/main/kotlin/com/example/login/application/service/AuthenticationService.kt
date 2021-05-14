package com.example.login.application.service

import com.example.login.domain.model.User
import com.example.login.domain.repository.UserRepository
import com.example.login.presentation.form.AuthenticationInformation
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


@Service
class AuthenticationService(
    private val userRepository: UserRepository
) {
    fun findUser(email: String): User? {
        return userRepository.find(email)
    }


    //疑似的に外部からPOST通信をしているかのようにしている
    fun authentication(authInfo: AuthenticationInformation){
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.set("Content-Type","application/x-www-form-urlencoded")
        val entity: HttpEntity<Any> = HttpEntity(headers)

        val builder: UriComponentsBuilder = UriComponentsBuilder
                            .fromHttpUrl("http://localhost:8080/login/auth")
                            .queryParam("email",authInfo.email)
                            .queryParam("pass",authInfo.pass)

        val response: ResponseEntity<String> = restTemplate.exchange(
            builder.toUriString(),
            HttpMethod.POST,
            entity,
            String::class.java
        )

    }
}
