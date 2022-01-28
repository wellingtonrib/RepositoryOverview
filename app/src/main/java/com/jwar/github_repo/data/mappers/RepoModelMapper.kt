package com.jwar.github_repo.data.mappers

import com.jwar.github_repo.core.shared.Mapper
import com.jwar.github_repo.data.local.entities.RepoEntity
import com.jwar.github_repo.domain.model.RepoModel

class RepoModelMapper: Mapper<RepoModel, RepoEntity> {
    override fun map(origin: RepoModel): RepoEntity =
        RepoEntity(
            uid = origin.id,
            htmlUrl = origin.htmlUrl,
            name = origin.name,
            fullName = origin.fullName,
            description = origin.description,
            forksCount = origin.forksCount,
            watchersCount = origin.watchersCount,
            subscribersCount = origin.subscribersCount,
            openIssuesCount = origin.openIssuesCount,
        )
}