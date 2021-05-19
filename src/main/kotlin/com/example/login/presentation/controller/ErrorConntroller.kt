package com.example.login.presentation.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ErrorConntroller {

    @GetMapping("/error")
    fun error (model: Model) :String{

        return "error"
    }
}