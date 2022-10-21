package com.itoll.githubusers.data.remote.server_model

import com.google.gson.annotations.SerializedName
import com.itoll.githubusers.domain.ui_model.User

data class UserDataModel(
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val events_url: String?,
    val followers_url: String?,
    val following_url: String?,
    val gists_url: String?,
    val gravatar_id: String?,
    val html_url: String?,
    @SerializedName("login")
    val userName: String?,
    val node_id: String?,
    val organizations_url: String?,
    val received_events_url: String?,
    val repos_url: String?,
    val site_admin: Boolean?,
    val starred_url: String?,
    val subscriptions_url: String?,
    val type: String?,
    val url: String?
)

fun UserDataModel.toUser(): User =
    User(
        id = id,
        avatarUrl = avatarUrl ?: "",
        userName = userName ?: ""

    )

