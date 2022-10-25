package com.itoll.githubusers.data.remote

import com.google.gson.GsonBuilder
import com.itoll.githubusers.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        private const val API_BASE_URL = BuildConfig.API_URL
        private const val CONNECTION_TIME_OUT = 10L
        private const val READ_TIME_OUT = 10L
        private const val WRITE_TIME_OUT = 10L

        private var baseRetrofit: Retrofit? = null
        private var gson = GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create()

        @Synchronized
        fun getRetrofitInstance(
        ): Retrofit = baseRetrofit?.run {
            this
        } ?: run {
            Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getBaseOkHttpClient())
                .build().apply {
                    baseRetrofit = this
                }

        }

        @Synchronized
        fun getBaseOkHttpClient()
                : OkHttpClient = OkHttpClient().newBuilder().addInterceptor {
            val originalRequest: Request = it.request()
            val builder = originalRequest.newBuilder().addHeader(
                "Content-Type", "application/json"
            ).addHeader("Authorization", System.getenv("token") ?: "")

            val newRequest = builder.build()
            it.proceed(newRequest)
        }.connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS).apply {
                if (BuildConfig.DEBUG) {
                    val httpLogInterceptor = HttpLoggingInterceptor()
                    httpLogInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(httpLogInterceptor)
                }
            }.run {
                build()
            }

    }

}