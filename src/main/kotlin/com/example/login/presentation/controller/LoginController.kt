package com.example.login.presentation.controller

import com.example.login.application.service.AuthenticationService
import com.example.login.application.service.UserService
import com.example.login.presentation.form.AuthenticationInformation
import com.example.login.presentation.form.LoginInfo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller //ルーティングができるようにする
@RequestMapping("login") //クラスに対してもルーティングができるようにする(下のloginやuserlist)
class LoginController (
    private val userService: UserService,
    private val authenticationService: AuthenticationService
        ){
    @GetMapping("")
    fun login(model: Model): String{
        model.addAttribute("authInfo",AuthenticationInformation("",""))
        return "login"
    }


    @PostMapping("/userlist")
    fun logintouserlist (@ModelAttribute authInfo: AuthenticationInformation, model: Model) :String{
        val userList=userService.getAllUser()
//        model.addAttribute("logininfo",loginInfo)
        model.addAttribute("userlist",userList)
        authenticationService.authentication(authInfo)

        return "userlist"
    }
}