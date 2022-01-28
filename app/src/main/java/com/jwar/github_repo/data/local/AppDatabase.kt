package com.jwar.github_repo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jwar.github_repo.data.local.dao.RepoDao
import com.jwar.github_repo.data.local.entities.RepoEntity

@Database(
    entities = [
        RepoEntity::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun repoDao(): RepoDao
}