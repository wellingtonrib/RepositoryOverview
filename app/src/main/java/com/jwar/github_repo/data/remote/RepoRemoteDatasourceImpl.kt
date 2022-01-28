package com.jwar.github_repo.data.remote

import com.jwar.github_repo.domain.datasources.RepoRemoteDataSource

class RepoRemoteDatasourceImpl(
    private val service: RepoService
): RepoRemoteDataSource {

    override suspend fun fetchIssues() = getResponse {
        service.fetchIssues()
    }

    override suspend fun fetchRepo() = getResponse {
        service.fetchRepo()
    }
}