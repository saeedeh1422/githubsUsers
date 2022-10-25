package com.itoll.githubusers.presentation.user_detail

import com.itoll.githubusers.domain.ui_model.UserDetail

sealed class UserDetailState {
    data class User(val userDetail : UserDetail?) : UserDetailState()
    data class Error(val message: String) : UserDetailState()
    object Loading : UserDetailState()

}

