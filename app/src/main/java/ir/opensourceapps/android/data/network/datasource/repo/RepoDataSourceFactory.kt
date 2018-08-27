package ir.opensourceapps.android.data.network.datasource.repo

import android.arch.lifecycle.MutableLiveData
import ir.opensourceapps.android.data.network.api.RepoApi
import ir.opensourceapps.android.model.Repo
import ir.opensourceapps.android.util.AppExecutors

class RepoDataSourceFactory(
        private val repoApi: RepoApi,
        private val appExecutors: AppExecutors) : android.arch.paging.DataSource.Factory<Int, Repo>() {
    val sourceLiveData = MutableLiveData<RepoDataSource>()
    override fun create(): RepoDataSource {
        val source = RepoDataSource(repoApi, appExecutors)
        sourceLiveData.postValue(source)
        return source
    }
}