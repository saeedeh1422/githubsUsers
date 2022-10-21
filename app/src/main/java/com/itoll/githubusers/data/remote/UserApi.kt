package com.itoll.githubusers.data.remote

import com.itoll.githubusers.data.remote.server_model.UserDetailDataModel
import com.itoll.githubusers.data.remote.server_model.UserDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<UserDataModel>

    @GET("users/{userName}")
    suspend fun getUserDetail(
        @Path("userName") userName: String
    ): UserDetailDataModel
}