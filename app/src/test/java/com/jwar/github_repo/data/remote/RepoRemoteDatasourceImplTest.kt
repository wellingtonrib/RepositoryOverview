package com.jwar.github_repo.data.remote

import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking

class RepoRemoteDatasourceImplTest : TestCase() {

    private val repoService: RepoService = mockk(relaxed = true)
    private val repoRemoteDatasourceImpl = RepoRemoteDatasourceImpl(repoService)

    fun testFetchIssues() = runBlocking {
        repoRemoteDatasourceImpl.fetchIssues()

        coVerify { repoService.fetchIssues() }
    }

    fun testFetchRepo() = runBlocking {
        repoRemoteDatasourceImpl.fetchRepo()

        coVerify { repoService.fetchRepo() }
    }
}