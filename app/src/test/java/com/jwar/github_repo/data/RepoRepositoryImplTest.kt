package com.jwar.github_repo.data

import com.jwar.github_repo.core.shared.Resource
import com.jwar.github_repo.core.shared.Status
import com.jwar.github_repo.data.local.entities.RepoEntity
import com.jwar.github_repo.data.mappers.RepoModelMapper
import com.jwar.github_repo.domain.datasources.RepoLocalDataSource
import com.jwar.github_repo.domain.datasources.RepoRemoteDataSource
import com.jwar.github_repo.domain.model.IssueModel
import com.jwar.github_repo.domain.model.RepoModel
import com.jwar.github_repo.util.readJsonFile
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

class RepoRepositoryImplTest : TestCase() {

    private val repo = readJsonFile<RepoModel>("mock_repo.json")
    private val issues = readJsonFile<List<IssueModel>>("mock_issues.json")
    private val remoteDataSource: RepoRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: RepoLocalDataSource = mockk(relaxed = true)
    private val repositoryImpl = RepoRepositoryImpl(remoteDataSource, localDataSource)
    private val repoEntity: RepoEntity by lazy { RepoModelMapper().map(repo) }

    fun testFetchIssues() = runBlocking {
        coEvery { remoteDataSource.fetchIssues() } returns Resource.success(issues)

        val result = repositoryImpl.fetchIssues().toList()

        coVerify { remoteDataSource.fetchIssues() }
        assertEquals(Status.LOADING, result[0].status)
        assertEquals(null, result[0].data)
        assertEquals(Status.SUCCESS, result[1].status)
        assertEquals(issues, result[1].data)
    }

    fun testFetchRepo() = runBlocking {
        coEvery { remoteDataSource.fetchRepo() } returns Resource.success(repo)

        val result = repositoryImpl.fetchRepo().toList()

        coVerify { remoteDataSource.fetchRepo() }
        assertEquals(Status.LOADING, result[0].status)
        assertEquals(null, result[0].data)
        assertEquals(Status.SUCCESS, result[1].status)
        assertEquals(repo, result[1].data)
        coVerify { localDataSource.saveRepo(repoEntity) }
    }

    fun testGetRepo() = runBlocking {
        coEvery { localDataSource.getRepo() } returns flowOf(repoEntity)

        val result = repositoryImpl.getRepo().first().data

        coVerify { localDataSource.getRepo() }
        assertEquals(1L, result?.id)
        assertEquals("Kotlin", result?.name)
        assertEquals("Jetbrains/Kotlin", result?.fullName)
        assertEquals("https://github.com/JetBrains/Kotlin", result?.htmlUrl)
        assertEquals("The Kotlin Programming Language", result?.description)
        assertEquals(100, result?.forksCount)
        assertEquals(100, result?.subscribersCount)
        assertEquals(100, result?.watchersCount)
        assertEquals(100, result?.openIssuesCount)
    }

    fun testGetRepoNull() = runBlocking {
        coEvery { localDataSource.getRepo() } returns flowOf(null)

        repositoryImpl.getRepo().collect()

        coVerify { remoteDataSource.fetchRepo() }
    }
}