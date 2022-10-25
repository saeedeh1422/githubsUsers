package com.itoll.githubusers.data.remote

import com.itoll.githubusers.data.remote.server_model.SearchUserDataModel
import com.itoll.githubusers.data.remote.server_model.UserDetailDataModel
import com.itoll.githubusers.data.remote.server_model.UserDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<UserDataModel>

    @GET("users/{userName}")
    suspend fun getUserDetail(
        @Path("userName") userName: String
    ): UserDetailDataModel

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") query: String
    ) : SearchUserDataModel

    @GET("users/{userName}/followers")
    suspend fun getFollowers(
        @Path("userName") userName: String
    ): List<UserDataModel>

}