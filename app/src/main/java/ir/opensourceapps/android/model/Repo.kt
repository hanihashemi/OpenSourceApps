package ir.opensourceapps.android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repo(
        val id: Long,
        val name: String,
        @field:SerializedName("full_name")
        val fullName: String,
        val description: String,
        val url: String,
        val homepage: String,
        @field:SerializedName("html_url")
        val htmlUrl: String,
        val language: String?,
        @field:SerializedName("open_issues")
        val openIssues: Int,
        val forks: Int,
        @field:SerializedName("stargazers_count")
        val stars: Int,
        val owner: Owner
) : Parcelable