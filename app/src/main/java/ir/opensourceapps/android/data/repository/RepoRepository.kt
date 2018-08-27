package ir.opensourceapps.android.data.repository

import android.arch.lifecycle.LiveData
import ir.opensourceapps.android.data.network.adapter.ApiResponse
import ir.opensourceapps.android.data.network.api.RepoApi
import ir.opensourceapps.android.model.Content
import ir.opensourceapps.android.util.AppExecutors

class RepoRepository(private val repoApi: RepoApi,
                     private val appExecutors: AppExecutors) {

    fun readme(owner: String, repo: String): LiveData<Resource<Content>> {
        return object : NetworkBoundResource<Content>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<Content>> {
                return repoApi.readme(owner, repo)
            }
        }.asLiveData()
    }
}