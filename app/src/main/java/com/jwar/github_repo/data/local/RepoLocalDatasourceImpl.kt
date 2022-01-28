package com.jwar.github_repo.data.local

import com.jwar.github_repo.data.local.dao.RepoDao
import com.jwar.github_repo.data.local.entities.RepoEntity
import com.jwar.github_repo.domain.datasources.RepoLocalDataSource

class RepoLocalDatasourceImpl(
    private val dao: RepoDao
): RepoLocalDataSource {

    override fun getRepo() = dao.getRepo()

    override fun saveRepo(entity: RepoEntity) {
        dao.insertRepo(entity)
    }

    override fun deleteAll() {
        dao.deleteAll()
    }

}