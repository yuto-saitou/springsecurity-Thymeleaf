package com.example.login.presentation.config

import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
class HttpSessionConfig {
    @Bean
    fun connectionFactory() : JedisConnectionFactory{
        return JedisConnectionFactory()
    }

}