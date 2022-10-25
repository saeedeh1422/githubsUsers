package com.itoll.githubusers.domain.use_case.user

import com.itoll.githubusers.base.ApiResult
import com.itoll.githubusers.data.remote.server_model.UserDataModel
import com.itoll.githubusers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUsersUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<ApiResult<List<UserDataModel>>>  = flow {
        emit(repository.getUsers())
    }

}