package com.itoll.githubusers.domain.use_case.user

import com.itoll.githubusers.base.ApiResult
import com.itoll.githubusers.data.remote.server_model.UserDataModel
import com.itoll.githubusers.data.remote.server_model.UserDetailDataModel
import com.itoll.githubusers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserDetailUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(userNAme: String): Flow<ApiResult<UserDetailDataModel>> = flow {
        emit(repository.getUserDetail(userNAme))
    }

}