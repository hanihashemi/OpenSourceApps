package ir.opensourceapps.android.model

import com.google.gson.annotations.SerializedName

data class Repo(
        val id: Long,
        @field:SerializedName("full_name")
        val full_name: String,
        val description: String,
        val url: String,
        val homepage: String,
        @field:SerializedName("html_url")
        val htmlUrl: String,
        val language: String,
        @field:SerializedName("open_issues")
        val openIssues: Int,
        val forks: Int,
        @field:SerializedName("stargazers_count")
        val stars: Int,
        val owner: Owner
)