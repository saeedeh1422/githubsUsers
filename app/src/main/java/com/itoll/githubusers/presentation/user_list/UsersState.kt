package com.itoll.githubusers.presentation.user_list

import com.itoll.githubusers.domain.ui_model.User

sealed class UsersState {
    data class Users(val users: List<User> ) : UsersState()
    data class Error(val message: String) : UsersState()
    object Loading : UsersState()
}


sealed class SearchState {
    data class SearchUsers(val users: List<User> ) : SearchState()
    data class Error(val message: String) : SearchState()
    object Loading : SearchState()
}

