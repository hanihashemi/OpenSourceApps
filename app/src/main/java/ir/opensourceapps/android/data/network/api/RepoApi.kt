package ir.opensourceapps.android.data.network.api

import android.arch.lifecycle.LiveData
import ir.opensourceapps.android.data.network.adapter.ApiResponse
import ir.opensourceapps.android.model.Content
import ir.opensourceapps.android.model.RepoSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoApi {
    @GET("search/repositories")
    fun search(
            @Query("q") query: String,
            @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 10,
            @Query("sort") sort: String = "stars",
            @Query("order") order: String = "desc"
    ): Call<RepoSearchResult>

    @GET("repos/{owner}/{repo}/readme")
    fun readme(
            @Path("owner") owner: String,
            @Path("repo") repo: String
    ) : LiveData<ApiResponse<Content>>
}