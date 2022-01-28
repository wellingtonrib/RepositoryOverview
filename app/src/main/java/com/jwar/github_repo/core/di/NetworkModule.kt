package com.jwar.github_repo.core.di

import com.jwar.github_repo.AppConfig.BASE_URL
import com.jwar.github_repo.BuildConfig
import com.jwar.github_repo.data.remote.RepoService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    factory {
        HttpLoggingInterceptor().setLevel(
            if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                if (BuildConfig.ACCESS_TOKEN.isNotEmpty()) {
                    request.addHeader("Authorization", "token ${BuildConfig.ACCESS_TOKEN}")
                }
                chain.proceed(request.build())
            }
            .build()
    }

    factory {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(RepoService::class.java)
    }

}