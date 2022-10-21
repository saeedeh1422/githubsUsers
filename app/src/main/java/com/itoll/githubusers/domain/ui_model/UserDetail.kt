package com.itoll.githubusers.domain.ui_model

data class UserDetail(
    val id: Long,
    val userName: String,
    val name: String,
    val avatarUrl: String,
    val email: String,
    val followers: Long,
    val following: Long,
    val location: String,

)
