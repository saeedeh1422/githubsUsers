package com.itoll.githubusers.presentation.user_list

import com.itoll.githubusers.base.BaseViewModel
import com.itoll.githubusers.domain.use_case.user.GetUsersUseCase

class UserListViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : BaseViewModel() {


}