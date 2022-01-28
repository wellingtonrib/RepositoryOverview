package com.jwar.github_repo.domain.repositories

import com.jwar.github_repo.domain.model.IssueModel
import com.jwar.github_repo.domain.model.RepoModel
import com.jwar.github_repo.core.shared.Resource
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    fun fetchIssues(): Flow<Resource<List<IssueModel>>>
    fun fetchRepo(): Flow<Resource<RepoModel>>
    fun getRepo(): Flow<Resource<RepoModel>>
}