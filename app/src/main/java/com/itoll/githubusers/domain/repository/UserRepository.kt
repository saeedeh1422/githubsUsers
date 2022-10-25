package com.itoll.githubusers.domain.repository

import com.itoll.githubusers.base.ApiResult
import com.itoll.githubusers.data.remote.server_model.SearchUserDataModel
import com.itoll.githubusers.data.remote.server_model.UserDataModel
import com.itoll.githubusers.data.remote.server_model.UserDetailDataModel

interface UserRepository {

    suspend fun getUsers() : ApiResult<List<UserDataModel>>

    suspend fun getUserDetail(userName : String) : ApiResult<UserDetailDataModel>

    suspend fun searchUser(q :String) : ApiResult<SearchUserDataModel>

    suspend fun getFollowers(userName: String) : ApiResult<List<UserDataModel>>

}