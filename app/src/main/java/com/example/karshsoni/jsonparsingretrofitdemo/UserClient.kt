package com.example.karshsoni.jsonparsingretrofitdemo

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserClient {
    @POST("/posts")
    fun createAccount(@Body user: User): Call<User>
}