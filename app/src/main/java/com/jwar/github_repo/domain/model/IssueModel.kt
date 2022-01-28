package com.jwar.github_repo.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Keep
@Parcelize
data class IssueModel(
        @SerializedName("html_url") var htmlUrl: String,
        @SerializedName("title") var title: String,
        @SerializedName("state") var state: String,
        @SerializedName("user") var user: UserModel,
        @SerializedName("created_at") var createdAt: Date
): Parcelable
