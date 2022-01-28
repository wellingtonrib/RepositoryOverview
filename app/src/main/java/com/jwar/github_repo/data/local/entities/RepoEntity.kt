package com.jwar.github_repo.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepoEntity(
    @PrimaryKey val uid: Long,
    @ColumnInfo(name = "html_url") var htmlUrl: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "full_name") var fullName: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "forks_count") var forksCount: Int,
    @ColumnInfo(name = "watchers_count") var watchersCount: Int,
    @ColumnInfo(name = "open_issues_count") var openIssuesCount: Int,
    @ColumnInfo(name = "subscribers_count") var subscribersCount: Int,
)