package com.jwar.github_repo.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class RepoModel(
    @SerializedName("id") var id: Long,
    @SerializedName("html_url") var htmlUrl: String,
    @SerializedName("name") var name: String,
    @SerializedName("full_name") var fullName: String,
    @SerializedName("description") var description: String,
    @SerializedName("forks_count") var forksCount: Int,
    @SerializedName("watchers_count") var watchersCount: Int,
    @SerializedName("open_issues_count") var openIssuesCount: Int,
    @SerializedName("subscribers_count") var subscribersCount: Int,
): Parcelable
