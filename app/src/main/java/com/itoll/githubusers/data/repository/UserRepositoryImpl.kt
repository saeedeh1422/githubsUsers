package com.itoll.githubusers.data.repository

import com.itoll.githubusers.base.ApiResult
import com.itoll.githubusers.base.BaseRepository
import com.itoll.githubusers.data.remote.UserApi
import com.itoll.githubusers.data.remote.server_model.SearchUserDataModel
import com.itoll.githubusers.data.remote.server_model.UserDataModel
import com.itoll.githubusers.data.remote.server_model.UserDetailDataModel
import com.itoll.githubusers.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userApi: UserApi
) : BaseRepository(), UserRepository {

    override suspend fun getUsers(): ApiResult<List<UserDataModel>> {
        return execute { userApi.getUsers() }
    }

    override suspend fun getUserDetail(userName: String): ApiResult<UserDetailDataModel> {
        return execute { userApi.getUserDetail(userName) }
    }

    override suspend fun searchUser(q: String): ApiResult<SearchUserDataModel> {
        return execute { userApi.searchUser(q) }
    }

    override suspend fun getFollowers(userName: String): ApiResult<List<UserDataModel>> {
        return execute { userApi.getFollowers(userName) }
    }


}