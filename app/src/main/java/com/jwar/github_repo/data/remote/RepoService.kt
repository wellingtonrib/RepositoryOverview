package com.jwar.github_repo.data.remote

import com.jwar.github_repo.AppConfig.OWNER
import com.jwar.github_repo.AppConfig.REPO
import com.jwar.github_repo.domain.model.IssueModel
import com.jwar.github_repo.domain.model.RepoModel
import retrofit2.Response
import retrofit2.http.*

interface RepoService {

    @GET("repos/$OWNER/$REPO")
    suspend fun fetchRepo(): Response<RepoModel>

    @GET("repos/$OWNER/$REPO/issues")
    suspend fun fetchIssues(): Response<List<IssueModel>>

}
