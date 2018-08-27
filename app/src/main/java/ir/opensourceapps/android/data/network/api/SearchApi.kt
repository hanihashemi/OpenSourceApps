package ir.opensourceapps.android.data.network.api

import ir.opensourceapps.android.model.RepoSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/repositories")
    fun search(
            @Query("q") query: String,
            @Query("sort") sort: String = "stars",
            @Query("order") order: String = "desc"
    ): Call<RepoSearchResult>
}