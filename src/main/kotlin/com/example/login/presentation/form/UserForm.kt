package com.example.login.presentation.form

import com.example.login.domain.enum.RoleType

//ログインページのフォームに入力した値を格納するデータクラス
data class AuthenticationInformation(
    val email: String,
    val pass: String
)

//入力したユーザー情報を格納するデータクラス
data class UserStatus(
    val id: Int,
    val email: String,
    val pass: String,
    val name: String,
    val roleType: RoleType
)