package com.itoll.githubusers.data.remote.server_model

import com.google.gson.annotations.SerializedName
import com.itoll.githubusers.domain.ui_model.UserDetail

data class UserDetailDataModel(
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val bio: Any?,
    val blog: String?,
    val company: Any?,
    val created_at: String?,
    val email: String?,
    val events_url: String?,
    val followers: Long?,
    val followers_url: String?,
    val following: Long?,
    val following_url: String?,
    val gists_url: String?,
    val gravatar_id: String?,
    val hireable: Any?,
    val html_url: String?,
    val location: String?,
    @SerializedName("login")
    val userName: String?,
    val name: String?,
    val node_id: String?,
    val organizations_url: String?,
    val public_gists: Int?,
    val public_repos: Int?,
    val received_events_url: String?,
    val repos_url: String?,
    val site_admin: Boolean?,
    val starred_url: String?,
    val subscriptions_url: String?,
    val twitter_username: Any?,
    val type: String?,
    val updated_at: String?,
    val url: String?
)

fun UserDetailDataModel.toUserDetail(): UserDetail =
    UserDetail(
        id = id,
        userName = userName ?: "",
        name = name ?: "",
        avatarUrl = avatarUrl ?: "",
        email = email ?: "",
        followers = followers ?: 0L,
        following = following ?: 0L,
        location = location ?: ""
    )
