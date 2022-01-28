package com.jwar.github_repo.data.local.dao

import androidx.room.*
import com.jwar.github_repo.data.local.entities.RepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {

    @Query("SELECT * FROM repoEntity LIMIT 1")
    fun getRepo(): Flow<RepoEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepo(repoEntity: RepoEntity)

    @Query("DELETE FROM repoEntity")
    fun deleteAll()

}