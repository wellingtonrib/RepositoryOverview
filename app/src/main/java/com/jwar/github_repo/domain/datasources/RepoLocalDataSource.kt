package com.jwar.github_repo.domain.datasources

import com.jwar.github_repo.data.local.entities.RepoEntity
import kotlinx.coroutines.flow.Flow

interface RepoLocalDataSource {
    fun getRepo(): Flow<RepoEntity?>
    fun saveRepo(entity: RepoEntity)
    fun deleteAll()
}