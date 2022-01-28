package com.jwar.github_repo.domain.datasources

import com.jwar.github_repo.core.shared.Resource
import com.jwar.github_repo.domain.model.IssueModel
import com.jwar.github_repo.domain.model.RepoModel
import retrofit2.Response
import timber.log.Timber

interface RepoRemoteDataSource {
    suspend fun fetchIssues(): Resource<List<IssueModel>>
    suspend fun fetchRepo(): Resource<RepoModel>

    suspend fun <T> getResponse(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Resource.success(body)
                }
            }
            Resource.error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Timber.e(e)
            Resource.error("Connection Error")
        }
    }

}