package ir.opensourceapps.android.data.network.datasource.repo

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import ir.opensourceapps.android.data.network.api.RepoApi
import ir.opensourceapps.android.data.network.datasource.NetworkState
import ir.opensourceapps.android.model.Repo
import ir.opensourceapps.android.model.RepoSearchResult
import ir.opensourceapps.android.util.AppExecutors
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class RepoDataSource(
        private val repoApi: RepoApi,
        private val appExecutors: AppExecutors) : PageKeyedDataSource<Int, Repo>() {

    private var retry: (() -> Any)? = null

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            appExecutors.networkIO().execute {
                it.invoke()
            }
        }
    }

    override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Repo>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        networkState.postValue(NetworkState.LOADING)
        repoApi.search(
                "android+language:java+kotlin",
                params.key,
                params.requestedLoadSize
        ).enqueue(
                object : retrofit2.Callback<RepoSearchResult> {
                    override fun onFailure(call: Call<RepoSearchResult>, t: Throwable) {
                        retry = {
                            loadAfter(params, callback)
                        }
                        networkState.postValue(NetworkState.error(t.message
                                ?: "unknown err"))
                    }

                    override fun onResponse(
                            call: Call<RepoSearchResult>,
                            response: Response<RepoSearchResult>) {
                        val items = response.body()?.items
                        retry = null
                        if (items != null && items.isNotEmpty())
                            callback.onResult(items, params.key + 1)
                        networkState.postValue(NetworkState.LOADED)
                    }
                }
        )
    }

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Repo>) {
        val request = repoApi.search(
                query = "android+language:java+kotlin", perPage = params.requestedLoadSize)
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        try {
            val response = request.execute()

            val items = response?.body()?.items
            retry = null
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            if (items != null && items.isNotEmpty())
                callback.onResult(items, 1, 2)
        } catch (exception: IOException) {
            retry = {
                loadInitial(params, callback)
            }
            val error = NetworkState.error(exception.message)
            networkState.postValue(error)
            initialLoad.postValue(error)
        }
    }
}