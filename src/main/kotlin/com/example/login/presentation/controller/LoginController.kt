package com.example.login.presentation.controller

import com.example.login.application.service.AdminUserService
import com.example.login.application.service.AuthenticationService
import com.example.login.application.service.UserService
import com.example.login.domain.model.User
import com.example.login.presentation.form.AuthenticationInformation
import com.example.login.presentation.form.UserStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller                        //ルーティングができるようにする
@RequestMapping("login") //クラスに対してもルーティングができるようにする(下のloginやuserlist)
class LoginController (
    private val authenticationService: AuthenticationService,
    private val userService: UserService,
    private val adminUserService: AdminUserService
    ){
    @GetMapping("")
    fun login(model: Model): String{//↓ログインページの入力値をauthInfoに渡す
        model.addAttribute("authInfo",AuthenticationInformation("",""))
        return "login"
    }

    @GetMapping("/userlist")
    fun logintouserlist (model: Model) :String{
        val userList=userService.getAllUser()
        model.addAttribute("userlist",userList)

        return "userlist"
    }

    @PostMapping("/insert")
    fun register(model: Model):String{
        model.addAttribute("userInfo",UserStatus("","","","",""))

        return "insert"
    }




    @PostMapping("")
    fun loginAuthentication(@ModelAttribute authInfo: AuthenticationInformation) : String{
//        authenticationService.authentication(authInfo)
        return "test"
    }
}