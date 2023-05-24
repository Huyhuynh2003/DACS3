package com.example.quancoffee

import retrofit2.http.Body
import retrofit2.http.POST
import com.example.quancoffee.Model.Account

interface API {
    @POST("register.php")
    suspend fun Register(@Body account:Account)

    @POST("login.php")
    suspend fun Login(@Body account:Account):Account
}