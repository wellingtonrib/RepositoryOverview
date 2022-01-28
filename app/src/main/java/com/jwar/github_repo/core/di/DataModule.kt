package com.jwar.github_repo.core.di

import androidx.room.Room
import com.jwar.github_repo.data.RepoRepositoryImpl
import com.jwar.github_repo.data.local.AppDatabase
import com.jwar.github_repo.data.local.RepoLocalDatasourceImpl
import com.jwar.github_repo.data.remote.RepoRemoteDatasourceImpl
import com.jwar.github_repo.domain.datasources.RepoLocalDataSource
import com.jwar.github_repo.domain.datasources.RepoRemoteDataSource
import com.jwar.github_repo.domain.repositories.RepoRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, "app.db"
        ).build()
    }

    single {
        get<AppDatabase>().repoDao()
    }

    single<RepoLocalDataSource> {
        RepoLocalDatasourceImpl(get())
    }

    single<RepoRemoteDataSource> {
        RepoRemoteDatasourceImpl(get())
    }

    single<RepoRepository> {
        RepoRepositoryImpl(get(), get())
    }

}