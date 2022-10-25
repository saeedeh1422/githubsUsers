package com.itoll.githubusers.di

import com.itoll.githubusers.data.remote.ApiClient
import com.itoll.githubusers.data.remote.UserApi
import com.itoll.githubusers.data.repository.UserRepositoryImpl
import com.itoll.githubusers.domain.repository.UserRepository
import com.itoll.githubusers.domain.use_case.user.GetFollowersUseCase
import com.itoll.githubusers.domain.use_case.user.GetUserDetailUseCase
import com.itoll.githubusers.domain.use_case.user.GetUserSearchUseCase
import com.itoll.githubusers.domain.use_case.user.GetUsersUseCase
import com.itoll.githubusers.presentation.user_detail.UserDetailViewModel
import com.itoll.githubusers.presentation.user_list.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val module = module {

    single<UserRepository>{
        UserRepositoryImpl(ApiClient.getRetrofitInstance().create(UserApi::class.java))
    }
    single {
        GetUsersUseCase(get())
    }
    single {
        GetUserSearchUseCase(get())
    }
    single {
        GetUserDetailUseCase(get())
    }
    single {
        GetFollowersUseCase(get())
    }
    viewModel {
        UserListViewModel(get(),get())
    }
    viewModel {
        UserDetailViewModel(get() ,get())
    }

}

val appDiModules = listOf(module)