package com.itoll.githubusers.presentation.user_detail

import androidx.lifecycle.viewModelScope
import com.itoll.githubusers.base.ApiResult
import com.itoll.githubusers.base.BaseViewModel
import com.itoll.githubusers.data.remote.server_model.toUser
import com.itoll.githubusers.data.remote.server_model.toUserDetail
import com.itoll.githubusers.domain.ui_model.User
import com.itoll.githubusers.domain.use_case.user.GetFollowersUseCase
import com.itoll.githubusers.domain.use_case.user.GetUserDetailUseCase
import kotlinx.coroutines.flow.*

class UserDetailViewModel(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val getFollowersUseCase: GetFollowersUseCase
) : BaseViewModel() {

    private val _userState = MutableStateFlow<UserDetailState>(UserDetailState.Loading)
    val userState: StateFlow<UserDetailState> = _userState

    private val _userList = MutableSharedFlow<List<User>>()
    val userList: SharedFlow<List<User>> = _userList

    fun getUserDetail(userName: String) {
        getUserDetailUseCase(userName).onEach { result ->
            when (result) {
                is ApiResult.Success -> {
                    _userState.value =
                        UserDetailState.User(result.data?.toUserDetail())
                }
                is ApiResult.Error -> {
                    _userState.value = UserDetailState.Error(result.message)
                }
            }
        }.launchIn(viewModelScope)

    }


    fun getFollowers(userName: String) {
        getFollowersUseCase(userName).onEach { result ->
            when (result) {
                is ApiResult.Success -> {
                    _userList.emit(result.data?.map { it.toUser() } ?: emptyList())
                }
                is ApiResult.Error -> {
                }
            }
        }.launchIn(viewModelScope)
    }
}