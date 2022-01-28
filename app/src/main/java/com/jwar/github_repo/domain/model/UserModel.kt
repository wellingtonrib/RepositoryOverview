package com.jwar.github_repo.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    @SerializedName("id") val id: Int,
    @SerializedName("avatar_url") val avatarUrl: String
): Parcelable