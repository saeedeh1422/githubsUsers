package com.itoll.githubusers.data.remote.server_model

import com.itoll.githubusers.domain.ui_model.SearchUser

data class SearchUserDataModel(
    val incomplete_results: Boolean?,
    val items: List<UserDataModel>?,
    val total_count: Long?
)


fun SearchUserDataModel.toSearchUser(): SearchUser =
    SearchUser(items?.map { it.toUser() } ?: emptyList())
