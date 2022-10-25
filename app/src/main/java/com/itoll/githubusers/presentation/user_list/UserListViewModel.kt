package com.itoll.githubusers.presentation.user_list

import androidx.lifecycle.viewModelScope
import com.itoll.githubusers.base.ApiResult
import com.itoll.githubusers.base.BaseViewModel
import com.itoll.githubusers.data.remote.server_model.toSearchUser
import com.itoll.githubusers.data.remote.server_model.toUser
import com.itoll.githubusers.domain.use_case.user.GetUserSearchUseCase
import com.itoll.githubusers.domain.use_case.user.GetUsersUseCase
import kotlinx.coroutines.flow.*

class UserListViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val searchUseCase: GetUserSearchUseCase
) : BaseViewModel() {
    private val _userListState = MutableStateFlow<UsersState>(UsersState.Loading)
    val userListState: StateFlow<UsersState> = _userListState

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Loading)
    val searchState: StateFlow<SearchState> = _searchState


    fun getUserList() {
        getUsersUseCase().onEach { result ->
            when (result) {
                is ApiResult.Success -> {
                    _userListState.value =
                        UsersState.Users(result.data?.map { it.toUser() } ?: emptyList())
                }
                is ApiResult.Error -> {
                    _userListState.value = UsersState.Error(result.message)
                }
            }
        }.launchIn(viewModelScope)

    }

    fun searchUser(q: String) {
        searchUseCase(q).onEach { result ->
            when (result) {
                is ApiResult.Success -> {
                    _searchState.value =
                        SearchState.SearchUsers(result.data?.toSearchUser()?.items ?: emptyList())
                }
                is ApiResult.Error -> {
                    _searchState.value = SearchState.Error(result.message)
                }
            }
        }.launchIn(viewModelScope)
    }

}