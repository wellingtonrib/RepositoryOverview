package com.jwar.github_repo.data

import com.jwar.github_repo.domain.model.IssueModel
import com.jwar.github_repo.domain.model.RepoModel
import com.jwar.github_repo.domain.repositories.RepoRepository
import com.jwar.github_repo.core.shared.Resource
import com.jwar.github_repo.core.shared.Status
import com.jwar.github_repo.data.mappers.RepoEntityMapper
import com.jwar.github_repo.data.mappers.RepoModelMapper
import com.jwar.github_repo.domain.datasources.RepoLocalDataSource
import com.jwar.github_repo.domain.datasources.RepoRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RepoRepositoryImpl(
    private val remoteDataSource: RepoRemoteDataSource,
    private val localDataSource: RepoLocalDataSource
): RepoRepository {

    override fun fetchIssues(): Flow<Resource<List<IssueModel>>> {

        return flow {
            emit(Resource.loading(null))
            val result = remoteDataSource.fetchIssues()
            emit(result)
        }.flowOn(Dispatchers.IO)

    }

    override fun fetchRepo(): Flow<Resource<RepoModel>> {

        return flow {
            emit(Resource.loading())
            val result = remoteDataSource.fetchRepo()
            if (result.status == Status.SUCCESS) {
                result.data?.let {
                    localDataSource.deleteAll()
                    localDataSource.saveRepo(
                        RepoModelMapper().map(it)
                    )
                }
            }
            emit(result)

        }.flowOn(Dispatchers.IO)

    }

    override fun getRepo(): Flow<Resource<RepoModel>> =

        localDataSource.getRepo().map { repo ->

            if (repo == null) { fetchRepo().collect() }
            Resource.success(repo?.let { RepoEntityMapper().map(it) })

        }.flowOn(Dispatchers.IO)

}