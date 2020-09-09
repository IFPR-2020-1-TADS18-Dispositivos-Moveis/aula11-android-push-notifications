package com.stiehl.testfcm.api

import com.stiehl.testfcm.api.inputs.MessageInput
import com.stiehl.testfcm.api.inputs.RegisterInput
import com.stiehl.testfcm.api.results.MessageResult
import com.stiehl.testfcm.api.results.RegisterResult
import com.stiehl.testfcm.api.results.UsersResult
import com.stiehl.testfcm.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface FirebaseService {
    @GET("users")
    fun getUsers(): Call<UsersResult>

    @Headers("Content-Type: application/json")
    @POST("register")
    fun register(@Body data: RegisterInput): Call<RegisterResult>

    @Headers("Content-Type: application/json")
    @POST("message")
    fun sendMessage(@Body data: MessageInput): Call<MessageResult>
}