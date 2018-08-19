package ir.opensourceapps.android.model

import com.google.gson.annotations.SerializedName

class RepoSearchResult(
        @field:SerializedName("total_count")
        val totalCount: Int,
        val items: List<Repo>
)