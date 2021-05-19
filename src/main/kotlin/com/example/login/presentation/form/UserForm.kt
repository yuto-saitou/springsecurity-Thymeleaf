package com.example.login.presentation.form

//ログインページのフォームに入力した値を格納するデータクラス
data class AuthenticationInformation(
    val email: String,
    val pass: String
)
