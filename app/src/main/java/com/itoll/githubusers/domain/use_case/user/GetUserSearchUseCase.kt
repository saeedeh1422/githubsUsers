package com.itoll.githubusers.domain.use_case.user

import com.itoll.githubusers.base.ApiResult
import com.itoll.githubusers.data.remote.server_model.SearchUserDataModel
import com.itoll.githubusers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserSearchUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(q: String): Flow<ApiResult<SearchUserDataModel>> = flow {
        emit(repository.searchUser(q))
    }

}