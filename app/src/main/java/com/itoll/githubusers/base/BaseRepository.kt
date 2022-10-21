package com.itoll.githubusers.base

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    fun <T> execute(response: T): ApiResult<T> {
        return try {
            ApiResult.Success(response)
        } catch (e: HttpException) {
            when (e.code()) {
                401 -> ApiResult.Error("خطای اطلاعات کاربری")
                429 -> ApiResult.Error("برای ارسال مجدد لطفا منتظر بمانید")
                in 400..499 -> ApiResult.Error(" خطا${e.code()}")
                in 500..599 -> ApiResult.Error("خطا سرور ${e.code()}")
                else -> ApiResult.Error("خطای عمومی")
            }
        } catch (e: IOException) {
            ApiResult.Error("مشکل ارتباط اینترنت")
        }
    }
}

sealed class ApiResult<out T> {
    data class Success<T>(val data: T?) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}