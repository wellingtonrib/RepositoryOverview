package com.jwar.github_repo.data.local

import com.jwar.github_repo.data.local.dao.RepoDao
import com.jwar.github_repo.data.local.entities.RepoEntity
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase

class RepoLocalDatasourceImplTest : TestCase() {

    private val repo: RepoEntity = mockk(relaxed = true)
    private val repoDao: RepoDao = mockk(relaxed = true)
    private val repoLocalDatasourceImpl = RepoLocalDatasourceImpl(repoDao)

    fun testGetRepo() {
        repoLocalDatasourceImpl.getRepo()

        verify { repoDao.getRepo() }
    }

    fun testInsertRepo() {
        repoLocalDatasourceImpl.saveRepo(repo)

        verify { repoDao.insertRepo(repo) }
    }

    fun testDeleteAll() {
        repoLocalDatasourceImpl.deleteAll()

        verify { repoDao.deleteAll() }
    }
}